package com.objectmentor.utilities;

import java.util.NoSuchElementException;
import static com.objectmentor.utilities.ArgsException.ErrorCode.*;

/**
 * Created by cllamach on 25/09/15.
 */
public class DoubleArgumentMarshaler implements ArgumentMarshaler {

    private double doubleValue = 0;

    public void set(String currentArgument) throws ArgsException {
        String parameter = null;
        try{
            parameter = currentArgument;
            doubleValue = Double.parseDouble(parameter);
        }
        catch (NoSuchElementException e){
            throw new ArgsException(MISSING_DOUBLE);
        }
        catch (NumberFormatException e){
            throw new ArgsException(INVALID_DOUBLE, parameter);
        }
    }

    public static double getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof DoubleArgumentMarshaler)
            return ((DoubleArgumentMarshaler)am).doubleValue;
        else
            return 0;
    }
}
