package com.github.tareforme.domain.expeptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() { super("You entered an invalid name. Please, check if the name isn't null or blank."); }
    public InvalidNameException(String message) {
        super(message);
    }
}
