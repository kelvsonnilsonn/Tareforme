package com.github.tareforme.domain.ports.service;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.model.DefaultUser;
import com.github.tareforme.domain.model.Task;

public interface TaskService {
    void create(String name, String Description, DefaultUser owner) throws InvalidNameException, InvalidDescriptionException;
    void delete(Long id);
    void delete(Task task);
    void update(Task task);
    Task findById(Long id);
}
