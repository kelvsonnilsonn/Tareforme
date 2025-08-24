package com.github.tareforme.domain.model;

import jakarta.persistence.DiscriminatorValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@DiscriminatorValue("ADMIN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Admin extends DefaultUser{

    public Admin(String name, String pass){
        super(name, pass);
    }

}
