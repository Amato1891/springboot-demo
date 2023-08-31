package com.amato.quizapp.service;

import com.amato.quizapp.dao.TaskDao;
import com.amato.quizapp.model.Question;
import com.amato.quizapp.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;

    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            return new ResponseEntity<>(taskDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>((new ArrayList<>()), HttpStatus.BAD_REQUEST);
    };

    @Transactional
    public void updateTaskReminder(int taskId) {
        taskDao.updateReminderById(taskId);
    }

    public ResponseEntity<String> addTask(@RequestBody Task task) {
        taskDao.save(task);
        return new ResponseEntity<> ("success",HttpStatus.CREATED);
    }

    public String deleteTask(int taskId) {
        taskDao.deleteById(taskId);
        return "Successfully deleted";
    }
}
