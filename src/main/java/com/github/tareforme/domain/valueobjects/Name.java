package com.github.tareforme.domain.valueobjects;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {
    private String name;

    public Name(String name){
        if(name == null || name.isBlank()){
            throw new InvalidNameException();
        }
        this.name = name;
    }

    /**
     * Creates a new Name object when the user wants to change it.
     * @param name refers to the new name passed in the Name change method.
     * */

    public static Name of(String name) throws InvalidNameException{
        return new Name(name);
    }
}
