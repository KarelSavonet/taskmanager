package com.example.taskmanager.repository;

import com.example.taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepositoryInterface extends JpaRepository<Task, Integer> {
}
