package com.example.taskmanager.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @NotEmpty
    private String title;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime deadline;

    @NotEmpty
    @Size(min = 3)
    private String description;

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    private List<Subtask> subtasks;


    public Task() {
        subtasks = new ArrayList<>();
    }

    public Task(String title, LocalDateTime deadline, String description) {
        this.title = title;
        this.deadline = deadline;
        this.description = description;
        subtasks = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getStringDeadline() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'at' HH:mm");
        return dateTimeFormatter.format(deadline);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask){
        subtasks.add(subtask);
    }
}
