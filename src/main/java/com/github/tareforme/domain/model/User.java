package com.github.tareforme.domain.model;

import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.expeptions.InvalidPasswordException;
import com.github.tareforme.domain.valueobjects.Name;
import com.github.tareforme.domain.valueobjects.Password;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════╗");
        sb.append("\n║                USER PROFILE            ║");
        sb.append("\n╠════════════════════════════════════════╣");
        sb.append(String.format("\n║ %-20s: %-16s ║", "ID", id));
        sb.append(String.format("\n║ %-20s: %-16s ║", "Username", getName()));
        sb.append(String.format("\n║ %-20s: %-15s ║", "Password", "🔒🔒🔒🔒🔒"));
        sb.append(String.format("\n║ %-20s: %-16s ║", "Tasks", tasks.size()));
        sb.append("\n╚════════════════════════════════════════╝");
        return sb.toString();
    }
}
