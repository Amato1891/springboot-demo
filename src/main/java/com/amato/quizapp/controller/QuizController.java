package com.amato.quizapp.controller;
import com.amato.quizapp.model.*;
import com.amato.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;


    @PostMapping("/create")
    public ResponseEntity<Object> createQuiz(@RequestParam String category, @RequestParam int numQ) {

        return quizService.createQuiz(category, numQ);
    }
    @PutMapping("/highscores/addNameHighscore/{quizId}")
    public ResponseEntity<Object> addNameHighscore(@PathVariable int quizId, @RequestBody String name) {
        quizService.addNameHighscore(quizId, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/highscores/getHighscores")
    public ResponseEntity<List<HighScores>> getHighscores() {
        return quizService.getHighscores();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses ) {
        return quizService.calculateResult(id, responses);
    }
}
