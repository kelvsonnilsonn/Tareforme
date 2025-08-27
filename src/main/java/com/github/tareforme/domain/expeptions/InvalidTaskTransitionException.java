package com.github.tareforme.domain.expeptions;

public class InvalidTaskTransitionException extends RuntimeException {
    public InvalidTaskTransitionException(String message) {
        super(message);
    }
}
