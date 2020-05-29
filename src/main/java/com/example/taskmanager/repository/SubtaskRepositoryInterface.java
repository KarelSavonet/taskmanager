package com.example.taskmanager.repository;

import com.example.taskmanager.domain.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepositoryInterface extends JpaRepository<Subtask, Integer> {
}
