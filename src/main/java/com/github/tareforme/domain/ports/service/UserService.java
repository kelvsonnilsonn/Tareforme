package com.github.tareforme.domain.ports.service;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.model.User;

public interface UserService {
    boolean create(String name, String pass) throws InvalidNameException, InvalidPasswordException;
    boolean delete(Long id);
    boolean delete(User user);
    boolean update(User user);
    User findById(Long id);
}
