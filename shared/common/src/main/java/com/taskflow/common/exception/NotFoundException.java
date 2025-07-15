package com.taskflow.common.exception;

public class NotFoundException extends RuntimeException {
    private final String entityName;

    public NotFoundException(String message, String entityName) {
        super(message);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
