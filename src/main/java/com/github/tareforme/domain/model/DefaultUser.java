package com.github.tareforme.domain.model;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.valueobjects.Name;
import com.github.tareforme.domain.valueobjects.Password;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "user_role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public abstract class DefaultUser{
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

    protected DefaultUser() {};

    public DefaultUser(String name, String pass){
        this.name = new Name(name);
        this.password = new Password(pass);
    }

    public boolean changePassword(String oPassword) throws InvalidPasswordException {
        this.password = Password.of(oPassword);
        return true;
    }

    public boolean changeName(String oName) throws InvalidNameException {
        this.name = Name.of(oName);
        return true;
    }
}
