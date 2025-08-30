package com.github.tareforme.domain.services;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.model.User;
import com.github.tareforme.domain.ports.service.UserService;
import com.github.tareforme.infra.repositories.UserRepository;
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
    public boolean create(String name, String pass) throws InvalidNameException, InvalidPasswordException {
        userDAO.save(new User(name, pass));
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try {
            userDAO.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        try {
            userDAO.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try{
            if(findById(user.getId()) != null){
                userDAO.save(user);
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public User findById(Long id){
        return userDAO.findById(id).orElse(null);
    }


}
