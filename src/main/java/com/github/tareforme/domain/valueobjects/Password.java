package com.github.tareforme.domain.valueobjects;

import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    private String password;

    public Password(String pass){
        validate(pass);
        this.password = pass;
    }

    /**
     * Creates a new Password object when the user wants to change it.
     * @param passwordToChange refers to the new password passed in the password change method.
     * */

    public static Password of(String passwordToChange) throws InvalidPasswordException{
        return new Password(passwordToChange);
    }

    private void validate(String passToValidate){
        if(passToValidate.length() < 3){
            throw new InvalidPasswordException();
        }
    }
}
