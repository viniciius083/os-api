package com.vinicius.os.controllers.exceptions;

public class FieldMessage {

    private String fieldName;

    private String error;

    public FieldMessage() {
    }

    public FieldMessage(String fieldName, String error) {
        this.fieldName = fieldName;
        this.error = error;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
