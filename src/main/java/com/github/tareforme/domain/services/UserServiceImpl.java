package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.model.User;
import com.github.tareforme.domain.ports.service.UserService;
import com.github.tareforme.infra.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userDAO;

    public UserServiceImpl(@Autowired UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void create(String name, String pass) throws InvalidNameException, InvalidPasswordException {
        userDAO.save(new User(name, pass));
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        if(!userDAO.existsById(id)){
            throw new EntityNotFoundException("User com o id" + id + " não encontrado.");
        }
        userDAO.delete(user);
    }
    @Override
    public void delete(User user) {
        if(!userDAO.existsById(user.getId())){
            throw new EntityNotFoundException("User não encontrado.");
        }
        userDAO.delete(user);
    }

    @Override
    public void update(User user) {
        if(!userDAO.existsById(user.getId())){
            throw new EntityNotFoundException("User não encontrado.");
        }
        userDAO.save(user);
    }

    @Override
    public User findById(Long id){
        return userDAO.findById(id).orElse(null);
    }
}
