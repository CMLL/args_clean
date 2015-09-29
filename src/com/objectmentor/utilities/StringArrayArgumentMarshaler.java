package com.objectmentor.utilities;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import static com.objectmentor.utilities.ArgsException.ErrorCode.*;

/**
 * Created by cllamach on 25/09/15.
 */
public class StringArrayArgumentMarshaler implements ArgumentMarshaler {

    private ArrayList arrayValue = new ArrayList();

    public void set(String currentArgument) throws ArgsException {
        try {
            for (String element : currentArgument.split(" "))
                arrayValue.add(element);
        }
        catch (NoSuchElementException e){
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static ArrayList getValue(ArgumentMarshaler am){
        if (am != null && am instanceof StringArrayArgumentMarshaler)
            return ((StringArrayArgumentMarshaler)am).arrayValue;
        else
            return null;
    }
}
