package com.github.tareforme.domain.valueobjects;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Description {
    private String description;

    public Description(String description){
        if(description == null || description.isBlank()){
            throw new InvalidDescriptionException();
        }
        this.description = description;
    }

    /**
     * Creates a new Description object when the user wants to change it.
     * @param description refers to the new description passed in the description change method.
     * */

    public static Description of(String description) throws InvalidDescriptionException{
        return new Description(description);
    }
}
