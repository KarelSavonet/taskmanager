package com.example.taskmanager.service;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskDTO;

import java.util.List;


public interface TaskServiceInterface {
    List<TaskDTO> getTasks();
    void addTask(TaskDTO taskDTO);
    void editTask(TaskDTO taskDTO);

}
