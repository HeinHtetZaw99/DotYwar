package com.daniel.appbase;

public class StatusVO {
    public enum TYPE {
        ERROR, NEUTRAL, POSITIVE, DIALOG
    }
    //close Type 1 for positive , 0 for negative
    private Object errorMsg = "";
    private TYPE errorType ;


    public StatusVO(Object msg, TYPE errorType) {
        this.errorMsg = msg;
        this.errorType = errorType;
    }


    public StatusVO() {
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public TYPE getErrorType() {
        return errorType;
    }

    public void setErrorType(TYPE errorType) {
        this.errorType = errorType;
    }
}
