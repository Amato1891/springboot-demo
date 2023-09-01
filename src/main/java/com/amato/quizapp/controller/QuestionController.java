package com.amato.quizapp.controller;
import com.amato.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amato.quizapp.model.Question;
import java.util.List;

@RestController

@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping ("/greeting")
    public String getGreeting() {
        return "Testing v1.0.2!";
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @DeleteMapping("/delete/{questionId}")
    public String deleteTask(@PathVariable int questionId){

        return questionService.deleteQuestion(questionId);
    }
}
