package com.github.tareforme.domain.ports.service;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.model.User;

import java.util.List;

public interface UserService {
    void create(String name, String pass) throws InvalidNameException, InvalidPasswordException;
    void delete(User user);
    void delete(Long id);
    void update(User user);
    List<User> findAll();
    User findById(Long id);
}
