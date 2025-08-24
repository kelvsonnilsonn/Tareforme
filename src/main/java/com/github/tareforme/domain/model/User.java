package com.github.tareforme.domain.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
public final class User extends DefaultUser {

    public User(String name, String pass){
        super(name, pass);
    }
}
