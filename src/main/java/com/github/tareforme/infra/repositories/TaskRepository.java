package com.github.tareforme.infra.repositories;

import com.github.tareforme.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
