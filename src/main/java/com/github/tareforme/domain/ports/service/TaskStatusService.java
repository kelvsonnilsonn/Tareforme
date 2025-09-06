package com.github.tareforme.domain.ports.service;

import com.github.tareforme.domain.model.Task;

public interface TaskStatusService {
    void setPedingStatus(Long taskId);
    void setCompleteStatus(Long taskId);
}
