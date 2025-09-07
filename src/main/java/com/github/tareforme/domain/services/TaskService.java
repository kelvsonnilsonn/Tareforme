package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.model.User;
import com.github.tareforme.infra.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskDAO;

    public TaskService(@Autowired TaskRepository taskDAO) {
        this.taskDAO = taskDAO;
    }

    /**
     * User creation procedure.
     *
     * @param name Refers to the name of the task to be created.
     * @param description Refers to the task description.
     * @param owner Refers to the creator of the task.
     *
     * @throws IllegalArgumentException If username is null or doesn't follow the name pattern (Thrown by the Username class constructor).
     * @throws IllegalArgumentException If password is null or less than 3 characters (Thrown by the Password class constructor).
     */

    public void create(String name, String description, User owner) throws InvalidNameException, InvalidDescriptionException {
        taskDAO.save(new Task(name, description, owner));
    }

    public void delete(Long id) {
        verifyIfExists(id);
        Task task = findById(id);
        taskDAO.delete(task);
    }

    public void delete(Task task) {
        verifyIfExists(task.getId());
        taskDAO.delete(task);
    }

    public void update(Task task) {
        verifyIfExists(task.getId());
        taskDAO.save(task);
    }

    public Task findById(Long id) {
        return taskDAO.findById(id).orElse(null);
    }

    public Page<Task> findAll(Pageable pageable) {
        return taskDAO.findAll(pageable);
    }

    public Page<Task> findByOwner(User owner, Pageable pageable) {
        return taskDAO.findByOwner(owner, pageable);
    }

    private void verifyIfExists(Long id){
        if(!taskDAO.existsById(id)){
            throw new EntityNotFoundException("Task not found.");
        }
    }
}
