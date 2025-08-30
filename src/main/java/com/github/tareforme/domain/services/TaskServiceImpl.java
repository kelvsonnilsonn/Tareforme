package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.model.DefaultUser;
import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.ports.service.TaskService;
import com.github.tareforme.infra.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskDAO;

    public TaskServiceImpl(@Autowired TaskRepository taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public void create(String name, String description, DefaultUser owner) throws InvalidNameException, InvalidDescriptionException {
        taskDAO.save(new Task(name, description, owner));
    }

    @Override
    public void delete(Long id) {
        Task task = findById(id);
        if(task == null){
            throw new EntityNotFoundException("Task com o id " + id + " não encontrada.");
        }
        taskDAO.delete(task);
    }

    @Override
    public void delete(Task task) {
        Task t = findById(task.getId());
        if(t == null){
            throw new EntityNotFoundException("Task não encontrada.");
        }
        taskDAO.delete(t);
    }

    @Override
    public void update(Task task) {
        Task t = findById(task.getId());
        if(t == null){
            throw new EntityNotFoundException("Task não encontrada.");
        }
        taskDAO.save(task);
    }

    @Override
    public Task findById(Long id) {
        return taskDAO.findById(id).orElse(null);
    }
}
