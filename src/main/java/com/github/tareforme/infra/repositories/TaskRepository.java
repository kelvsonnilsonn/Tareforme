package com.github.tareforme.infra.repositories;

import com.github.tareforme.domain.model.User;
import com.github.tareforme.domain.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOwner(User owner);
    Page<Task> findByOwner(User owner, Pageable pageable);
}
