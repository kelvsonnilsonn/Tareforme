package com.github.tareforme.domain.valueobjects;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import com.github.tareforme.domain.expeptions.InvalidNameException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class that stores the most basic task data: name and description.
 * Uses the Factory Method pattern for creating new instances with
 * modified data, ensuring immutability and validation:
 * - {@link #ofName(String)}: Creates a new instance with a modified name.
 * - {@link #ofDescription(String)}: Creates a new instance with a modified description.
 *
 * <p>This is an Embeddable class and will be used as a component in the Task entity in JPA</p>
 *
 * @see Name
 * @see Description
 * @see InvalidNameException
 * @see InvalidDescriptionException
 *
 * @author Kelvson Nilson
 * @version 1.1
 * */

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

    /**
     * Method to change the task name by creating a new Name object.
     * @param oName refers to the new name to be used as the task name.
     * @throws InvalidNameException thrown if the name does not comply with the defined pattern.
     */
    public TaskInfo ofName(String oName) throws InvalidNameException {
       return new TaskInfo(oName, this.description.getDescription());
    }

    /**
     * Method to change the task description by creating a new Description object.
     * @param oDescription refers to the new description to be used.
     * @throws InvalidDescriptionException thrown if the description does not comply with the defined pattern.
     * */
    public TaskInfo ofDescription(String oDescription) throws InvalidDescriptionException {
        return new TaskInfo(this.name.getName(), oDescription);
    }

    public String getName(){
        return this.name.getName();
    }

    public String getDescription(){
        return this.description.getDescription();
    }
}
