package com.example.taskmanager.domain;

import java.util.List;

public interface TaskDatabaseInterface {
    List<Task> getTasks();
    void addTask(Task task);
}
