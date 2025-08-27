package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void criandoUmUsuarioComSucesso() {
        assertTrue(userService.create("Pedro", "Joao1234"));
    }

    @Test
    void tentandoCriarUmUsuarioComNomeInvalido() {
        assertThrows(InvalidNameException.class, () -> userService.create(null, "Joao1234"));
    }
    @Test
    void tentandoCriarUmUsuarioComSenhaInvalida() {
        assertThrows(InvalidPasswordException.class,() -> userService.create("Pedro", "Jo"));
    }

    @Test
    void encontrandoComSucessoUmUserNoBD() {
        assertNotNull(userService.findById(1L));
    }
    @Test
    void naoDeveEncontrarUserComIDInvalido() {
        assertNull(userService.findById(3L));
    }
}