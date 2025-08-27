package com.github.tareforme.domain.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ADMIN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Admin extends DefaultUser{

    public Admin(String name, String pass){
        super(name, pass);
    }

}
