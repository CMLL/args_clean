package com.objectmentor.utilities;

import java.util.NoSuchElementException;
import static com.objectmentor.utilities.ArgsException.ErrorCode.*;

/**
 * Created by cllamach on 25/09/15.
 */
public class IntegerArgumentMarshaler implements ArgumentMarshaler
{
    private int intValue = 0;

    public void set(String currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument;
            intValue = Integer.parseInt(parameter);
        }
        catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_INTEGER);
        }
        catch (NumberFormatException e) {
            throw new ArgsException(INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof IntegerArgumentMarshaler)
            return ((IntegerArgumentMarshaler) am).intValue;
        else
            return 0;
    }
}
