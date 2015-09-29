package com.objectmentor.utilities;


import java.util.*;
import static com.objectmentor.utilities.ArgsException.ErrorCode.*;

/**
 * Created by cllamach on 25/09/15.
 */
public class CleanArgs {

    private Map<Character, ArgumentMarshaler> marshalers;
    private Set<Character> argsFound;
    private String currentArgument;

    public CleanArgs(String schema, String[] args) throws ArgsException {
        marshalers = new HashMap<Character, ArgumentMarshaler>();
        argsFound = new HashSet<>();

        parseSchema(schema);
        parseArgumentStrings(Arrays.asList(args));
    }

    private void parseSchema(String schema) throws ArgsException {
        for (String element : schema.split(","))
            if (element.length() > 0)
                parseSchemaElement(element.trim());
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);

        validateSchemaElementId(elementId);

        if (elementTail.length() == 0)
            marshalers.put(elementId, new BooleanArgumentMarshaler());
        else if (elementTail.equals("*"))
            marshalers.put(elementId, new StringArgumentMarshaler());
        else if (elementTail.equals("#"))
            marshalers.put(elementId, new IntegerArgumentMarshaler());
        else if (elementTail.equals("##"))
            marshalers.put(elementId, new DoubleArgumentMarshaler());
        else if (elementTail.equals("[*]"))
            marshalers.put(elementId, new StringArrayArgumentMarshaler());
        else
            throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId))
            throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
    }

    private void parseArgumentStrings(List<String> argsList) throws ArgsException {
        ListIterator argsIter = argsList.listIterator();
        while (argsIter.hasNext()) {
            String argString = (String) argsIter.next();
            if (argString.startsWith("-")) {
                if (argsIter.hasNext())
                    currentArgument = (String) argsIter.next();
                parseArgumentCharacters(argString.substring(1));
            }
        }
    }

    private void parseArgumentCharacters(String argChars) throws ArgsException {
        for (int i = 0; i < argChars.length(); i++)
            parseArgumentCharacter(argChars.charAt(i));
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException {
        ArgumentMarshaler m = marshalers.get(argChar);
        if (m == null) {
            throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
        }
        else {
            argsFound.add(argChar);
            try {
                m.set(currentArgument);
            }
            catch (ArgsException e) {
                e.setErrorArgumentId(argChar);
                throw e;
            }
        }
    }

    public boolean has (char arg) {
        return argsFound.contains(arg);
    }


    public boolean getBoolean(char arg){
        return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public String getString(char arg){
        return StringArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public int getInteger(char arg){
        return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public double getDouble(char arg){
        return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public ArrayList getList(char arg){
        return StringArrayArgumentMarshaler.getValue(marshalers.get(arg));
    }

}
