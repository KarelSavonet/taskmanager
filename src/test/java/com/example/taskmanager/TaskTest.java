package com.example.taskmanager;

import com.example.taskmanager.domain.Subtask;
import com.example.taskmanager.domain.Task;
import org.aspectj.lang.annotation.Before;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskTest {

    @Test
    public void constructor_with_no_params_initializes_subtasks(){
        // method to be tested
        Task emptyTask = new Task();

        // checks
        assertNotEquals(emptyTask.getSubtasks(),null);
    }

    @Test
    public void constructor_with_params_sets_attributes(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();

        // method to be tested
        Task task = new Task(title,deadline,description);

        // checks
        assertEquals(title,task.getTitle());
        assertEquals(description,task.getDescription());
        assertEquals(deadline,task.getDeadline());
    }

    @Test
    public void getTitle_returns_title(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();
        Task task = new Task(title,deadline,description);

        // method to be tested
        String tasksTitle = task.getTitle();

        // checks
        assertEquals(title,tasksTitle);
    }

    @Test
    public void getDeadline_returns_deadline(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();
        Task task = new Task(title,deadline,description);

        // method to be tested
        LocalDateTime tasksDeadline = task.getDeadline();

        // checks
        assertEquals(deadline,tasksDeadline);
    }

    @Test
    public void getDescription_returns_description(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();
        Task task = new Task(title,deadline,description);

        // method to be tested
        String tasksDescription = task.getDescription();

        // checks
        assertEquals(description,tasksDescription);
    }

    @Test
    public void getStringDeadline_returns_deadline_formatted(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.of(1998,1,9,13,37);
        Task task = new Task(title,deadline,description);
        String expected = "09 januari 1998 at 13:37";

        // method to be tested
        String actual = task.getStringDeadline();

        // checks
        assertEquals(expected,actual);

    }

    @Test
    public void setTitle_changes_title(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();
        Task task = new Task(title,deadline,description);
        assertEquals(title,"title");

        // method to be tested
        task.setTitle("other title");

        // checks
        assertEquals("other title",task.getTitle());
    }

    @Test
    public void setDeadline_changes_deadline(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();
        Task task = new Task(title,deadline,description);

        // method to be tested
        LocalDateTime newDeadline = LocalDateTime.of(1998,1,9,13,37);

        task.setDeadline(newDeadline);

        // checks
        assertEquals(newDeadline,task.getDeadline());
    }

    @Test
    public void setDescription_changes_description(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();
        Task task = new Task(title,deadline,description);

        // method to be tested
        task.setDescription("other description");

        // checks
        assertEquals("other description",task.getDescription());
    }

    @Test
    public void addSubtask_adds_subtask(){
        // setup
        String title = "title";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();
        Task task = new Task(title,deadline,description);

        // method to be tested
        task.addSubtask(new Subtask());

        // checks
        assertEquals(1,task.getSubtasks().size());
    }

/*    @Test(expected = ServiceException.class)
    @Transactional
    public void constructor_with_empty_title_throws_exception(){
        // setup
        String title = "";
        String description = "description";
        LocalDateTime deadline = LocalDateTime.now();

        // method to be tested
        Task task = new Task("",deadline,"a");
        System.out.println("ja");
    }*/


}
