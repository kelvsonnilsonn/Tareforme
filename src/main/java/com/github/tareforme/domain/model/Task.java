package com.github.tareforme.domain.model;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.valueobjects.TaskInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private TaskInfo taskInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    private Instant createdAt;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    public Task(String name, String description, User owner){
        this.taskInfo = new TaskInfo(name, description);
        this.owner = owner;
        this.createdAt = Instant.now();
        this.status = TaskStatus.CREATED;
    }

    public void changeTaskName(String oName) throws InvalidNameException {
        this.taskInfo = taskInfo.ofName(oName);
    }

    public void changeTaskDescription(String oDescription){
        this.taskInfo = taskInfo.ofDescription(oDescription);
    }

    public String getName(){
        return this.taskInfo.getName();
    }

    public String getDescription(){
        return this.taskInfo.getDescription();
    }

    @Override
    public String toString() {
        return "📋 Task [" + id + "]\n" +
                "├── 📛 Name: " + (taskInfo != null ? taskInfo.getName() : "null") + "\n" +
                "├── 📝 Description: " + (taskInfo != null ?
                (taskInfo.getDescription().length() > 30 ?
                        taskInfo.getDescription().substring(0, 30) + "..." :
                        taskInfo.getDescription()) : "null") + "\n" +
                "├── 👤 Owner: " + (owner != null ? owner.getName() : "null") + "\n" +
                "├── ⏰ Created: " + (createdAt != null ?
                createdAt.toString().replace("T", " ").replace("Z", "") : "null") + "\n" +
                "└── 🏷️ Status: " + (status != null ? status : "null") + "\n";
    }
}
