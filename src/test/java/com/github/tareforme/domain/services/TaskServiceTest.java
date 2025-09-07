package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.model.Task;
import com.github.tareforme.domain.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Test
    void criandoUmaTaskComONomeInvalido_DeveLançarUmaExceptionPorNull(){
        User user = userService.findById(1L);
        assertThrows(InvalidNameException.class, () ->taskService.create(null, "Uma descrição", user));
    }

    @Test
    void criandoUmaTaskComONomeInvalido_DeveLançarUmaExceptionPorBlank(){
        User user = userService.findById(1L);
        assertThrows(InvalidNameException.class, () ->taskService.create("", "Uma descrição", user));
    }

    @Test
    void tentandoDeletarUmaTaskCujoIdNãoFoiEncontradoNoBanco() {
        assertThrows(EntityNotFoundException.class, () -> taskService.delete(2L));
    }

    @Test
    void tentandoAtualizarONomeDeUmaTask() {
        Task task = taskService.findById(1L);
        String oldname = task.getName();
        task.changeTaskName("NomeAtualizado");
        taskService.update(task);
        assertNotEquals(oldname, taskService.findById(1L).getName());
    }

    @Test
    void tentandoAtualizarADescriçãoDeUmaTask() {
        Task task = taskService.findById(2L);
        String olddesc = task.getDescription();
        task.changeTaskDescription("Descrição Atualizada2");
        taskService.update(task);
        assertNotEquals(olddesc, taskService.findById(2L).getDescription());
    }

    @Test
    void tentandoDeletarUmaTaskComIDPresenteNoBanco() {
        assertDoesNotThrow(() -> taskService.delete(1L));
    }
}