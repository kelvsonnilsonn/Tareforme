package com.github.tareforme.domain.model;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import com.github.tareforme.domain.expeptions.InvalidNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void criandoUmaTaskEMudandoONomeDela() {
        Task task = new Task("Arrumá a casa", "não importa", new User("Joao", "12345"));
        assertTrue(task.changeTaskName("Arrumar a casa"));
    }

    @Test
    void criandoUmaTaskEMudandoONomeDelaParaUmNomeInvalido() {
        Task task = new Task("Arrumá a casa", "não importa", new User("Joao", "12345"));
        assertThrows(InvalidNameException.class, () -> task.changeTaskName(" "));
    }

    @Test
    void criandoUmaTaskEMudandoONomeDelaParaUmNomeInvalidoComNull() {
        Task task = new Task("Arrumá a casa", "não importa", new User("Joao", "12345"));
        assertThrows(InvalidNameException.class, () -> task.changeTaskName(null));
    }

    @Test
    void criandoUmaTaskEMudandoADescricaoDela() {
        Task task = new Task("não importa", "não importa", new User("Joao", "12345"));
        assertTrue(task.changeTaskDescription("Tem que varrer e correr"));
    }

    @Test
    void criandoUmaTaskEMudandoADescricaoDelaParaUmaDescricaoInvalida() {
        Task task = new Task("não importa", "não importa", new User("Joao", "12345"));
        assertThrows(InvalidDescriptionException.class, () -> task.changeTaskDescription(""));
    }

    @Test
    void criandoUmaTaskEMudandoADescricaoDelaParaUmaDescricaoInvalidaComNull() {
        Task task = new Task("não importa", "não importa", new User("Joao", "12345"));
        assertThrows(InvalidDescriptionException.class, () -> task.changeTaskDescription(null));
    }
}