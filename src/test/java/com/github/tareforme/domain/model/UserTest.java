package com.github.tareforme.domain.model;

import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void tentandoAlterarASenhaDoUsuarioParaUmaMenorQueOMinimo() {
        User user = new User("Joao", "Kelvson");
        assertThrows(InvalidPasswordException.class, () -> user.changePassword("ko"));
    }

    @Test
    void tentandoAlterarASenhaDoUsuarioParaUmaValida() {
        User user = new User("Joao", "Kelvson");
        assertTrue(user.changePassword("Kelvons123"));
    }
}