package com.taskflow.common.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String entityName;

    public NotFoundException(String message, String entityName) {
        super(message);
        this.entityName = entityName;
    }
}
