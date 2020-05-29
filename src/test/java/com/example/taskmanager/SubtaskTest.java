package com.example.taskmanager;

import com.example.taskmanager.domain.Subtask;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class SubtaskTest {
    @Test
    public void constructor_with_title_and_description_makes_subtask(){
        // setup
        String title = "title";
        String description = "description";

        // methods to test
        Subtask subtask = new Subtask(title,description);

        // checks
        assertNotEquals(null,subtask);
    }

    @Test
    public void getTitle(){
        // setup
        String title = "title";
        String description = "description";
        Subtask subtask = new Subtask(title,description);

        // methods to test
        String subtaskTitle = subtask.getTitle();

        // checks
        assertEquals(title,subtaskTitle);

    }

    @Test
    public void getDescription(){
        // setup
        String title = "title";
        String description = "description";
        Subtask subtask = new Subtask(title,description);

        // methods to test
        String subtaskDescription = subtask.getDescription();

        // checks
        assertEquals(description,subtaskDescription);
    }

    @Test
    public void setTitle(){
        // setup
        String title = "title";
        String description = "description";
        Subtask subtask = new Subtask(title,description);

        // methods to test
        subtask.setTitle("setTitle");

        // checks
        assertEquals("setTitle",subtask.getTitle());
    }

    @Test
    public void setDescription(){
        // setup
        String title = "title";
        String description = "description";
        Subtask subtask = new Subtask(title,description);

        // methods to test
        subtask.setDescription("setDescription");

        // checks
        assertEquals("setDescription",subtask.getDescription());
    }
}
