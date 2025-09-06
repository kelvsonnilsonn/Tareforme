package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.model.User;
import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.ports.service.TaskService;
import com.github.tareforme.infra.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskDAO;

    public TaskServiceImpl(@Autowired TaskRepository taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public void create(String name, String description, User owner) throws InvalidNameException, InvalidDescriptionException {
        taskDAO.save(new Task(name, description, owner));
    }

    @Override
    public void delete(Long id) {
        verifyIfExists(id);
        Task task = findById(id);
        taskDAO.delete(task);
    }

    @Override
    public void delete(Task task) {
        verifyIfExists(task.getId());
        taskDAO.delete(task);
    }

    @Override
    public void update(Task task) {
        verifyIfExists(task.getId());
        taskDAO.save(task);
    }

    @Override
    public Task findById(Long id) {
        return taskDAO.findById(id).orElse(null);
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskDAO.findAll(pageable);
    }

    @Override
    public Page<Task> findByOwner(User owner, Pageable pageable) {
        return taskDAO.findByOwner(owner, pageable);
    }

    private void verifyIfExists(Long id){
        if(!taskDAO.existsById(id)){
            throw new EntityNotFoundException("Task not found.");
        }
    }
}
