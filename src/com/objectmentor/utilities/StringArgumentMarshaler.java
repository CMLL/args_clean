package com.objectmentor.utilities;


import java.util.NoSuchElementException;
import static com.objectmentor.utilities.ArgsException.ErrorCode.*;

/**
 * Created by cllamach on 25/09/15.
 */
public class StringArgumentMarshaler implements ArgumentMarshaler {

    private String stringValue = "";

    public void set(String currentArgument) throws ArgsException {
        try {
            stringValue = currentArgument;
        }
        catch (NoSuchElementException e){
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static String getValue(ArgumentMarshaler am){
        if (am != null && am instanceof StringArgumentMarshaler)
           return ((StringArgumentMarshaler) am).stringValue;
        else
            return "";
    }
}
