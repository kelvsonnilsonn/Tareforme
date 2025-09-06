package com.github.tareforme.domain.services;

import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskStatusServiceImplTest {

    @Autowired
    private TaskStatusServiceImpl taskStatusService;
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private UserServiceImpl userService;

    @Test
    void tentandoFazerUmaTaskCreatedVirarPending_temQueDarCerto() {
        taskStatusService.setPedingStatus(2L);
        assertEquals(TaskStatus.PENDING, taskService.findById(2L).getStatus());
    }

    @Test
    void tentandoFazerUmaTaskPendingVirarCompleted_temQueDarCerto() {
        Task task = taskService.findById(1L);
    }

    @Test
    void tentandoFazerUmaTaskPendingVirarPending_temQueFalhar() {
        Task task = taskService.findById(1L);
    }

    @Test
    void tentandoFazerUmaTaskCreatedVirarCompleted_temQueFalhar() {
        Task task = taskService.findById(1L);
    }

    @Test
    void tentandoFazerUmaTaskCompletedVirarCompleted_temQueFalhar() {
        Task task = taskService.findById(1L);
    }
}