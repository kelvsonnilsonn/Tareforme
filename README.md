# Task Manager 📝

Um CRUD simples para gerenciamento de tarefas desenvolvida em Java 21 com Spring Boot 3.5.5 e JPA.

## ✨ Funcionalidades

### 👥 Gerenciamento de Usuários
- ✅ Criar usuário com validação de nome e senha
- ✅ Listar todos os usuários
- ✅ Buscar usuário por ID
- ✅ Atualizar informações do usuário
- ✅ Excluir usuário

### 📋 Gerenciamento de Tarefas
- ✅ Criar tarefas com nome e descrição
- ✅ Listar todas as tarefas (com paginação)
- ✅ Buscar tarefas por proprietário
- ✅ Atualizar nome e descrição das tarefas
- ✅ Excluir tarefas
- ✅ Gerenciar status das tarefas (CREATED → PENDING → COMPLETED)

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **Lombok**
- **Maven**

## 📦 Entidades Principais

### User 👤
```java
@Entity
@Table(name = "users")
public class User {
    private Long id;
    private Name name;          // Value Object
    private Password password;  // Value Object  
    private List<Task> tasks = new ArrayList<>();    // Tarefas do usuário
}
```

### Task 📝
``` java
@Entity
@Table(name = "tasks")
public class Task {
    private Long id;
    private TaskInfo taskInfo;  // Value Object (nome + descrição)
    private User owner;         // Proprietário da tarefa
    private Instant createdAt;  // Data de criação
    private TaskStatus status;  // Status (CREATED, PENDING, COMPLETED)
}
```

## 🎯 Objetos de Valor

### Name
- Validação: não nulo e não vazio

- Name.of(String name) - Factory method

### Password
- Validação: mínimo 3 caracteres

- Password.of(String password) - Factory method

### Description
- Validação: não nulo e não vazio

- Description.of(String description) - Factory method

### TaskInfo
- Agrega nome e descrição da tarefa

- ofName() - Cria nova instância com nome modificado
- ofDescription() - Cria nova instância com descrição modificada

## 🔄 Fluxo de Status das Tarefas
``` text
CREATED → PENDING → COMPLETED
```

- CREATED: Estado inicial da tarefa
- PENDING: Tarefa em andamento (apenas de CREATED)
- COMPLETED: Tarefa concluída (apenas de PENDING)

## 🧪 Validações e Exceções
### Exceções Personalizadas
- InvalidNameException - Nome inválido
- InvalidPasswordException - Senha inválida (mínimo 3 caracteres)
- InvalidDescriptionException - Descrição inválida
- InvalidTaskTransitionException - Transição de status inválida
- EntityNotFoundException - Entidade não encontrada
