package com.example.taskmanager;

import com.example.taskmanager.domain.SubtaskDTO;
import com.example.taskmanager.domain.TaskDTO;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Test
    @Transactional
    public void getTasks_returns_tasks(){
        // setup
        addTaskToService();

        // method to be tested
        List<TaskDTO> tasks = taskService.getTasks();

        // checks
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertNotNull(tasks.get(0));

    }

    @Test
    @Transactional
    public void getTask_returns_task(){
        // setup
        addTaskToService();

        // method to be tested
        TaskDTO taskDTO = taskService.getTask(1);

        // checks
        assertNotNull(taskDTO);
        assertEquals("title",taskDTO.getTitle());
    }

    @Test
    @Transactional
    public void addSubtask_adds_subtask(){
        // setup
        addTaskToService();
        int taskid = taskService.getTasks().get(0).getId();

        // method to be tested
        taskService.addSubtask(taskid,new SubtaskDTO());

        // checks
        assertNotNull(taskService.getTask(taskid).getSubtasks());
        assertFalse(taskService.getTask(taskid).getSubtasks().isEmpty());
    }

    private void addTaskToService(){
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(title);
        taskDTO.setDeadline(deadline);
        taskDTO.setDescription(description);
        taskDTO.setId(1);
        taskService.addTask(taskDTO);
    }

}
