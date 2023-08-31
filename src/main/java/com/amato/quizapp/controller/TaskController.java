package com.amato.quizapp.controller;
import com.amato.quizapp.model.Task;
import com.amato.quizapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequestMapping("/task")
public class TaskController {
    @Autowired

    private TaskService taskService;

    @GetMapping("/allTasks")
    public ResponseEntity<List<Task>> getTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/updateReminder/{taskId}")
    public ResponseEntity<Object> updateReminder(@PathVariable int taskId) {
        taskService.updateTaskReminder(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addTask")
    public ResponseEntity<Object> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable int taskId) {

        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
