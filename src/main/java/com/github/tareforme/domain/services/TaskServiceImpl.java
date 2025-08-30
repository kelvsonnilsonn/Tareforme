package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.model.DefaultUser;
import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.ports.service.TaskService;
import com.github.tareforme.infra.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskDAO;

    public TaskServiceImpl(@Autowired TaskRepository taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public boolean create(String name, String description, DefaultUser owner) throws InvalidNameException, InvalidDescriptionException {
        taskDAO.save(new Task(name, description, owner));
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try{
            taskDAO.delete(findById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Task task) {
        try{
            taskDAO.delete(task);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public Task findById(Long id) {
        return taskDAO.findById(id).orElse(null);
    }
}
