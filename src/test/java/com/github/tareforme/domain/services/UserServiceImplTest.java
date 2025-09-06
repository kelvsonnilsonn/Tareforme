package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.model.User;
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
        userService.create("Joao", "pedro");
    }

    @Test
    void tentandoCriarUmUsuarioComNomeInvalido_null() {
        assertThrows(InvalidNameException.class, () -> userService.create(null, "Joao1234"));
    }

    @Test
    void tentandoCriarUmUsuarioComNomeInvalido_blank() {
        assertThrows(InvalidNameException.class, () -> userService.create("", "Joao1234"));
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

    @Test
    void tentandoAtualizarONomeDeUsuarioUsuarioValido() {
        User user = userService.findById(1L);
        String oldname = user.getName();
        user.changeName("Roberto");
        userService.update(user);
        assertNotEquals(oldname, userService.findById(1L).getName());
    }

    @Test
    void tentandoAtualizarASenhaDesuarioValido() {
        User user = userService.findById(1L);
        String oldpass = user.getPassword();
        user.changePassword("123456665");
        userService.update(user);
        assertNotEquals(oldpass, userService.findById(1L).getPassword());
    }
}