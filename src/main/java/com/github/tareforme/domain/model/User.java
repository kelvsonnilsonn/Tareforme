package com.github.tareforme.domain.model;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.valueobjects.Name;
import com.github.tareforme.domain.valueobjects.Password;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Password password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Task> tasks;

    protected User() {};

    public User(String name, String pass){
        this.name = new Name(name);
        this.password = new Password(pass);
    }

    public void changePassword(String oPassword) throws InvalidPasswordException {
        this.password = Password.of(oPassword);
    }

    public void changeName(String oName) throws InvalidNameException {
        this.name = Name.of(oName);
    }

    public String getName(){
        return this.name.getName();
    }

    public String getPassword(){
        return this.password.getPassword();
    }
}
