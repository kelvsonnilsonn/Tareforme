package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidTaskTransitionException;
import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.model.TaskStatus;
import com.github.tareforme.domain.ports.service.TaskStatusService;
import com.github.tareforme.infra.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    @Autowired
    private TaskRepository taskDAO;

    /**
     * Changes the task status to {@code PENDING}.
     *
     * <p>This method retrieves the task from the database and transitions its status
     * to PENDING if the current status allows this transition.</p>
     *
     * @param taskId the ID of the task to be updated
     * @throws EntityNotFoundException if no task exists with the specified ID
     * @throws InvalidTaskTransitionException if the task is already in PENDING status.
     */

    @Override
    public void setPedingStatus(Long taskId) {
        Task task = taskDAO.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        if(task.getStatus() == TaskStatus.PENDING){
            throw new InvalidTaskTransitionException("The task's already pending.");
        }
        task.setStatus(TaskStatus.PENDING);
    }

    /**
     * Changes the task status to {@code COMPLETED}.
     *
     * <p>This method retrieves the task from the database and transitions its status
     * to COMPLETED if the current status allows this transition. A task can only be
     * completed if it is currently in PENDING status.</p>
     *
     * @param taskId the ID of the task to be updated
     * @throws EntityNotFoundException if no task exists with the specified ID
     * @throws InvalidTaskTransitionException if the task is already in COMPLETED status
     *                                        or if the task is in CREATED status (must be PENDING first)
     */

    @Override
    public void setCompleteStatus(Long taskId) {
        Task task = taskDAO.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        if(task.getStatus() == TaskStatus.COMPLETED){
            throw new InvalidTaskTransitionException("The task has already been compleated");
        }
        if(task.getStatus() == TaskStatus.CREATED){
            throw new InvalidTaskTransitionException("The task must be in Pending to be completed.");
        }
        task.setStatus(TaskStatus.COMPLETED);
    }
}
