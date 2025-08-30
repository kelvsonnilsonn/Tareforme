package com.github.tareforme.domain.ports.service;

import com.github.tareforme.domain.expeptions.InvalidDescriptionException;
import com.github.tareforme.domain.expeptions.InvalidNameException;
import com.github.tareforme.domain.model.DefaultUser;
import com.github.tareforme.domain.model.Task;

public interface TaskService {
    boolean create(String name, String Description, DefaultUser owner) throws InvalidNameException, InvalidDescriptionException;
    boolean delete(Long id);
    boolean delete(Task task);
    Task findById(Long id);
}
