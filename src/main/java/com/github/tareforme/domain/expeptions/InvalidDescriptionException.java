package com.github.tareforme.domain.expeptions;

public class InvalidDescriptionException extends RuntimeException {
    public InvalidDescriptionException() { super("You entered an invalid task description."); }
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
