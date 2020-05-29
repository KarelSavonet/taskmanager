package com.example.taskmanager.service;

import com.example.taskmanager.domain.*;
import com.example.taskmanager.repository.SubtaskRepositoryInterface;
import com.example.taskmanager.repository.TaskRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceInterface {
    //private List<Task> tasks;
    private final TaskRepositoryInterface taskRepo;
    private final SubtaskRepositoryInterface subtaskRepo;

    //@Autowired
    //private TaskDatabase taskDatabase;

    @Autowired
    public TaskService(TaskRepositoryInterface taskRepo, SubtaskRepositoryInterface subtaskRepo) {
        this.taskRepo = taskRepo;
        this.subtaskRepo = subtaskRepo;
    }

    @Override
    public List<TaskDTO> getTasks() {
        //return taskDatabase.getTasks();
        return taskRepo.findAll().stream().map(this::getTaskDTOFromTask).collect(Collectors.toList());
    }

    public void addTask(TaskDTO taskDTO) {
        //taskDatabase.addTask(task);
        Task task = this.getTaskFromTaskDTO(taskDTO);
        taskRepo.save(task);
    }

    public TaskDTO getTask(int id) {
        //return taskDatabase.getTask(id);
        Optional<Task> task = taskRepo.findById(id);
        TaskDTO taskDTO = getTaskDTOFromTask(task.orElseThrow(() -> new IllegalArgumentException("Task doesn't exist in the database.")));
        return taskDTO;
    }

    private Task getTaskFromRepo(int id){
        Optional<Task> task = taskRepo.findById(id);
        return task.orElseThrow(() -> new IllegalArgumentException("Task doesn't exist in the database."));
    }

    public void editTask(TaskDTO taskDTO) {
        //taskDatabase.editTask(task);
        Task task = this.getTaskFromTaskDTO(taskDTO);
        int id = task.getId();
        Task taskToBeUpdated = getTaskFromTaskDTO(this.getTask(id));
        taskToBeUpdated.setTitle(task.getTitle());
        taskToBeUpdated.setDescription(task.getDescription());
        taskToBeUpdated.setDeadline(task.getDeadline());
        taskRepo.save(taskToBeUpdated);
    }

/*    public List<SubtaskDTO> getSubtasks(int id) {
        //return taskDatabase.getSubtasks(id);
        Task task = getTaskFromTaskDTO(this.getTask(id));
        return task.getSubtasks().stream().map(this::getSubtaskDTOFromSubtask).collect(Collectors.toList());
    }*/

    public void addSubtask(int taskId, SubtaskDTO subtaskDTO){
        Subtask s = getSubtaskFromSubtaskDTO(subtaskDTO);
        Task task = getTaskFromRepo(taskId);
        s.setTask(task);
        subtaskRepo.save(s);
        task.addSubtask(s);
        taskRepo.save(task);
    }

    private Task getTaskFromTaskDTO(TaskDTO taskDTO){
        Task task = new Task();
        task.setDeadline(taskDTO.getDeadline());
        task.setDescription(taskDTO.getDescription());
        task.setTitle(taskDTO.getTitle());
        task.setId(taskDTO.getId());
        return task;
    }

    private TaskDTO getTaskDTOFromTask(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setId(task.getId());
        taskDTO.setSubtasks(task.getSubtasks().stream().map(this::getSubtaskDTOFromSubtask).collect(Collectors.toList()));
        return taskDTO;
    }

    private Subtask getSubtaskFromSubtaskDTO(SubtaskDTO subtaskDTO){
        Subtask subtask = new Subtask();
        subtask.setDescription(subtaskDTO.getDescription());
        subtask.setTitle(subtaskDTO.getTitle());
        subtask.setSubtaskId(subtaskDTO.getSubtaskId());
        return subtask;
    }

    private SubtaskDTO getSubtaskDTOFromSubtask(Subtask subtask){
        SubtaskDTO subtaskDTO = new SubtaskDTO();
        subtaskDTO.setDescription(subtask.getDescription());
        subtaskDTO.setTitle(subtask.getTitle());
        subtaskDTO.setSubtaskId(subtask.getSubtaskId());
        return subtaskDTO;
    }
}
