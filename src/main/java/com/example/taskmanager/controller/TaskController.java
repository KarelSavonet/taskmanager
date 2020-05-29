package com.example.taskmanager.controller;

import com.example.taskmanager.domain.Subtask;
import com.example.taskmanager.domain.SubtaskDTO;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskDTO;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    public TaskController(){

    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model){
        model.addAttribute("tasks",taskService.getTasks());
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getDetailsTask(@PathVariable("id") int id, Model model){
        TaskDTO requestedTask = taskService.getTask(id);
        //List<SubtaskDTO> subtasks = requestedTask.getSubtasks();
        model.addAttribute("requestedTask",requestedTask);
        //model.addAttribute("subtasks",subtasks);
        return "detailsTask";
    }

    @GetMapping("/tasks/new")
    public String toAddTaskForm(Model model){
        model.addAttribute("taskDTO",new TaskDTO());
        return "addTaskForm";
    }

    @PostMapping("/addTask") // moet zelfde zijn als action van form
    public String addTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "addTaskForm";
        }
        this.taskService.addTask(taskDTO);
        return "redirect:/tasks"; // redirect naar overzicht van tasks
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable int id, Model model){
        model.addAttribute("taskDTO",taskService.getTask(id));
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "editTask";
        }
        this.taskService.editTask(taskDTO);
        return "redirect:/tasks/"+taskDTO.getId(); // redirect naar details van de task
    }

    @GetMapping("/tasks/{id}/sub/create")
    public String toAddSubtaskForm(Model model, @PathVariable int id){
        model.addAttribute("subtaskDTO",new SubtaskDTO());
        model.addAttribute("id",id);
        return "addSubtaskForm";
    }

    @PostMapping("/addSubtask")
    public String addSubtask(@ModelAttribute @Valid SubtaskDTO subtaskDTO, BindingResult bindingResult, @RequestParam(value = "id") int id, Model model){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("id",id);
            return "addSubtaskForm";
        }
        taskService.addSubtask(id,subtaskDTO);
        /*Task requestedTask = taskService.getTask(id);
        requestedTask.addSubtask(subtask);
        model.addAttribute("requestedTask",requestedTask);
        model.addAttribute("subtasks",requestedTask.getSubtasks());*/
        return "redirect:/tasks/" + id;
    }













}
