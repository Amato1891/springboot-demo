package com.amato.quizapp.service;
import com.amato.quizapp.dao.HighscoreDao;
import com.amato.quizapp.dao.QuestionDao;
import com.amato.quizapp.dao.QuizDao;
import com.amato.quizapp.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    HighscoreDao highscoreDao;
    public ResponseEntity<List<HighScores>> getHighscores() {
        try {
            return new ResponseEntity<>(highscoreDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>((new ArrayList<>()), HttpStatus.BAD_REQUEST);
    };

    public ResponseEntity<Object> createQuiz(String category, int numQ) {

        // Format LocalDateTime using the formatter
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDate = currentDateTime.format(dateFormatter);
        String formattedTime = currentDateTime.format(timeFormatter);
        // End date time format
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTest_category(category);
        quiz.setDate_created(formattedDate);
        quiz.setTime_created(formattedTime);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        String id = quiz.getId().toString();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Quiz Created Successfully");
        response.put("id", id);
        response.put("category", category);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    @Transactional
    public void addNameHighscore(int quizId, String name) {
        highscoreDao.updateNameByQuizId(quizId, name);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int size = questions.size();
        int right = 0;
        int i = 0;
        String category = questions.get(0).getCategory();
        for(Response response : responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }

        int score = (right * 100) / size;
        HighScores highscores = new HighScores();
        highscores.setQuizId(id);
        highscores.setCategory(category);
        highscores.setTotalQuestions(size);
        highscores.setCorrectAnswers(right);
        highscores.setScore(score);
        highscoreDao.save(highscores);
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
