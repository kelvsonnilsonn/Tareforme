package com.github.tareforme.domain.ports.service;

import com.github.tareforme.domain.model.Task;

public interface TaskStatusService {
    boolean setPedingStatus(Task task);
    boolean setCompleteStatus(Task task);
}
