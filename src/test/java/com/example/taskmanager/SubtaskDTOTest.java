package com.example.taskmanager;

import com.example.taskmanager.domain.SubtaskDTO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class SubtaskDTOTest {
    @Test
    public void getTitle(){
        // setup
        String title = "title";
        String description = "description";
        SubtaskDTO subtaskDTO = new SubtaskDTO();
        subtaskDTO.setTitle(title);

        // methods to test
        String subtaskTitle = subtaskDTO.getTitle();

        // checks
        assertEquals(title,subtaskTitle);

    }

    @Test
    public void getDescription(){
        // setup
        String title = "title";
        String description = "description";
        SubtaskDTO subtaskDTO = new SubtaskDTO();
        subtaskDTO.setDescription(description);

        // methods to test
        String subtaskDescription = subtaskDTO.getDescription();

        // checks
        assertEquals(description,subtaskDescription);
    }

}
