package com.objectmentor.utilities;

import javax.print.DocFlavor;

/**
 * Created by cllamach on 25/09/15.
 */
public class ArgsException extends Exception {
    private char errorArgumentId = '\0';
    private String errorParameter = null;
    private ErrorCode code = null;

    public ArgsException() {}

    public ArgsException(String message) {super(message);}

    public ArgsException(ErrorCode code) {
        this.code = code;
    }

    public ArgsException(ErrorCode code, String errorParameter) {
        this.code = code;
        this.errorParameter = errorParameter;
    }

    public ArgsException(ErrorCode code, char errorArgumentId, String errorParameter) {
        this.code = code;
        this.errorParameter = errorParameter;
        this.errorArgumentId = errorArgumentId;
    }

    public char getErrorArgumentId(){
        return errorArgumentId;
    }

    public String getErrorParameter(){
        return errorParameter;
    }

    public void setErrorArgumentId(char argumentId) {
        errorArgumentId = argumentId;
    }

    public void setErrorParameter(String parameter) {
        errorParameter = parameter;
    }

    public ErrorCode getErrorCode() {
        return code;
    }

    public void setErrorCode(ErrorCode error_code) {
        code = error_code;
    }

    public String errorMessage() {
        switch (code) {
            case OK:
                return "TILT: Should not get here.";
            case UNEXPECTED_ARGUMENT:
                return String.format("Argument -%c unexpected", errorArgumentId);
            case MISSING_STRING:
                return String.format("Could not find string parameter for -%c", errorArgumentId);
            case INVALID_INTEGER:
                return String.format("Argument -%c expected integer but was %s.", errorArgumentId, errorParameter);
            case MISSING_INTEGER:
                return String.format("Count not find integer parameter for -%c", errorArgumentId);
            case INVALID_DOUBLE:
                return String.format("Argument -%c expects a double but got %s instead", errorArgumentId,
                        errorParameter);
            case MISSING_DOUBLE:
                return String.format("Could not find double parameter for -%c", errorArgumentId);
            case INVALID_ARGUMENT_NAME:
                return String.format("%c is not a valida argument name", errorArgumentId);
            case INVALID_ARGUMENT_FORMAT:
                return String.format("'%s' is not a valid argument format", errorParameter);
        }
        return "";
    }

    public enum ErrorCode {
        OK, UNEXPECTED_ARGUMENT, MISSING_STRING, INVALID_INTEGER, MISSING_INTEGER,
        INVALID_DOUBLE, MISSING_DOUBLE, INVALID_ARGUMENT_NAME, INVALID_ARGUMENT_FORMAT
    }
}
