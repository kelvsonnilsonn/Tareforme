package com.github.tareforme.domain.valueobjects;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskInfo {
    @Embedded
    private Name name;
    @Embedded
    private Description description;

    public TaskInfo(String name, String description){
        this.name = new Name(name);
        this.description = new Description(description);
    }

    public TaskInfo ofName(String oName) throws InvalidNameException {
       return new TaskInfo(oName, this.description.getDescription());
    }

    public TaskInfo ofDescription(String oDescription){
        return new TaskInfo(this.name.getName(), oDescription);
    }
}
