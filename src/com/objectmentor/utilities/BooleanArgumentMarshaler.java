package com.objectmentor.utilities;

/**
 * Created by cllamach on 25/09/15.
 */
public class BooleanArgumentMarshaler implements ArgumentMarshaler{

    private boolean booleanValue = false;

    public void set(String currentArgument) throws ArgsException {
        booleanValue = true;
    }

    public static boolean getValue(ArgumentMarshaler am){
        if (am != null && am instanceof BooleanArgumentMarshaler) {
            return ((BooleanArgumentMarshaler) am).booleanValue;
        }
        else {
            return false;
        }
    }
}
