package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidTaskTransitionException;
import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.model.TaskStatus;
import com.github.tareforme.domain.ports.service.TaskStatusService;
import com.github.tareforme.infra.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    @Autowired
    private TaskRepository taskDAO;

    @Override
    public boolean setPedingStatus(Task task) {
        if(task.getStatus() == TaskStatus.PENDING){
            throw new InvalidTaskTransitionException("A task já está em andamento.");
        }
        task.setStatus(TaskStatus.PENDING);
        taskDAO.save(task);
        return true;
    }

    @Override
    public boolean setCompleteStatus(Task task) {
        if(task.getStatus() == TaskStatus.COMPLETED){
            throw new InvalidTaskTransitionException("A task já foi completada.");
        }
        if(task.getStatus() == TaskStatus.CREATED){
            throw new InvalidTaskTransitionException("A task precisa estar em andamento para ser completada.");
        }
        task.setStatus(TaskStatus.COMPLETED);
        taskDAO.save(task);
        return true;
    }
}
