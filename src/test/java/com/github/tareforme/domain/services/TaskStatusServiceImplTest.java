package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidTaskTransitionException;
import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.model.TaskStatus;
import com.github.tareforme.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskStatusServiceImplTest {

    @Autowired
    private TaskStatusServiceImpl taskStatusService;

    @Test
    void tentandoFazerUmaTaskCreatedVirarPending_temQueDarCerto() {

        User user = new User("Pedro", "Pedro1234");
        Task task = new Task("Testando", "Uma descrição qualquer", user);
        assertTrue(taskStatusService.setPedingStatus(task));
    }

    @Test
    void tentandoFazerUmaTaskPendingVirarPending_temQueFalhar() {

        User user = new User("Pedro", "Pedro1234");
        Task task = new Task("Testando", "Uma descrição qualquer", user);
        task.setStatus(TaskStatus.PENDING);
        assertThrows(InvalidTaskTransitionException.class, () -> taskStatusService.setPedingStatus(task));
    }

    @Test
    void tentandoFazerUmaTaskCreatedVirarCompleted_temQueFalhar() {

        User user = new User("Pedro", "Pedro1234");
        Task task = new Task("Testando", "Uma descrição qualquer", user);
        assertThrows(InvalidTaskTransitionException.class, () -> taskStatusService.setCompleteStatus(task));
    }
}