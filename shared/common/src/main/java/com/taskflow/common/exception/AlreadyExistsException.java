package com.taskflow.common.exception;

import lombok.Getter;

public class AlreadyExistsException extends RuntimeException {
    private final String entityName;

    public AlreadyExistsException(String message, String entityName) {
        super(message);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
