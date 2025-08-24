package com.github.tareforme.domain.expeptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() { super("The password is less than the minimum length."); }
    public InvalidPasswordException(String message) {
        super(message);
    }
}
