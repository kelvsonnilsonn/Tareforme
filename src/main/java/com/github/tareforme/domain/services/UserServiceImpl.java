package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.model.User;
import com.github.tareforme.domain.ports.service.UserService;
import com.github.tareforme.infra.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userDAO;

    public UserServiceImpl(@Autowired UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * User creation procedure.
     *
     * @param name Refers to the name of the user to be created.
     * @param pass Refers to the user's password.
     *
     * @throws IllegalArgumentException If username is null or doesn't follow the name pattern (Thrown by the Username class constructor).
     * @throws IllegalArgumentException If password is null or less than 3 characters (Thrown by the Password class constructor).
     */

    @Override
    public void create(String name, String pass) throws InvalidNameException, InvalidPasswordException {
        userDAO.save(new User(name, pass));
    }

    @Override
    public void delete(Long id) {
        verifyIfExists(id);
        userDAO.deleteById(id);
    }
    @Override
    public void delete(User user) {
        verifyIfExists(user.getId());
        userDAO.delete(user);
    }

    @Override
    public void update(User user) {
        verifyIfExists(user.getId());
        userDAO.save(user);
    }

    @Override
    public List<User> findAll(){
        return userDAO.findAll();
    }

    @Override
    public User findById(Long id){
        return userDAO.findById(id).orElse(null);
    }

    private void verifyIfExists(Long id){
        if(!userDAO.existsById(id)){
            throw new EntityNotFoundException("User not found.");
        }
    }
}
