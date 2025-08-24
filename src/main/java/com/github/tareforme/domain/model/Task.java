package com.github.tareforme.domain.model;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.valueobjects.Description;
import com.github.tareforme.domain.valueobjects.Name;
import com.github.tareforme.domain.valueobjects.TaskInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TaskInfo taskInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")

    private DefaultUser owner;

    private final Instant createdAt;

    public Task(String name, String description, DefaultUser owner){
        this.taskInfo = new TaskInfo(name, description);
        this.owner = owner;
        this.createdAt = Instant.now();
    }

    public boolean changeTaskName(String oName) throws InvalidNameException {
        this.taskInfo = taskInfo.ofName(oName);
        return true;
    }

    public boolean changeTaskDescription(String oDescription){
        this.taskInfo = taskInfo.ofDescription(oDescription);
        return true;
    }
}
