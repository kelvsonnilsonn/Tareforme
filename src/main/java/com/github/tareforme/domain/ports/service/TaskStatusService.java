package com.github.tareforme.domain.ports.service;

import com.github.tareforme.domain.model.Task;

public interface TaskStatusService {
    void setPedingStatus(Task task);
    void setCompleteStatus(Task task);
}
