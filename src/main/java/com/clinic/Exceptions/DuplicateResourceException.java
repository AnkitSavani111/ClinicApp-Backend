package com.clinic.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateResourceException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public DuplicateResourceException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s already exists with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
