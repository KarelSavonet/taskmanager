package com.example.taskmanager;

import com.example.taskmanager.domain.TaskDTO;
import org.hibernate.service.spi.ServiceException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class TaskDTOTest {
    @Test
    public void constructor_with_no_params_initializes_subtasks(){
        // method to be tested
        TaskDTO emptyTaskDTO = new TaskDTO();

        // checks
        assertNotEquals(emptyTaskDTO.getSubtasks(),null);
    }

    @Test
    public void getTitle_returns_title(){
        // setup
        String title = "title";
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(title);

        // method to be tested
        String tasksTitle = taskDTO.getTitle();

        // checks
        assertEquals(title,tasksTitle);
    }

    @Test
    public void getDeadline_returns_deadline(){
        // setup
        LocalDateTime deadline = LocalDateTime.now();
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDeadline(deadline);

        // method to be tested
        LocalDateTime tasksDeadline = taskDTO.getDeadline();

        // checks
        assertEquals(deadline,tasksDeadline);
    }

    @Test
    public void getDescription_returns_description(){
        // setup
        String description = "description";
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription(description);
        // method to be tested
        String tasksDescription = taskDTO.getDescription();

        // checks
        assertEquals(description,tasksDescription);
    }

    @Test
    public void getStringDeadline_returns_deadline_formatted(){
        // setup
        LocalDateTime deadline = LocalDateTime.of(1998,1,9,13,37);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDeadline(deadline);
        String expected = "09 januari 1998 at 13:37";

        // method to be tested
        String actual = taskDTO.getStringDeadline();

        // checks
        assertEquals(expected,actual);

    }

}
