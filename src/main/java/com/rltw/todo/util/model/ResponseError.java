package com.rltw.todo.util.model;

import org.springframework.stereotype.Component;

import java.util.Map;

public class ResponseError {
    private String message="Something went wrong.";
    private Map<String,String> fieldErrors;

    public ResponseError(){}

    public ResponseError(String message) {
        this.message = message;
    }

    public ResponseError(String message, Map<String, String> fieldErrors) {
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
