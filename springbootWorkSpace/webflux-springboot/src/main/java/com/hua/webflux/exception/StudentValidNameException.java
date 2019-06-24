package com.hua.webflux.exception;

/**
 * 验证名称异常
 */
public class StudentValidNameException extends RuntimeException{
    private String objectName;
    private String field;
    private String message;

    public StudentValidNameException(String message, String objectName, String field) {
        super(message);
        this.objectName = objectName;
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
