package com.hust.nhakhoa.Exceptions;


public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    Integer fieldValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Integer fieldValue) {
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue){
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
