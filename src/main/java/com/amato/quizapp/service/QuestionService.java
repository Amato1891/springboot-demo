package com.amato.quizapp.service;
import com.amato.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.amato.quizapp.model.Question;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>((new ArrayList<>()), HttpStatus.BAD_REQUEST);
    };
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<> (questionDao.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>((new ArrayList<>()), HttpStatus.BAD_REQUEST);
    };

    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        questionDao.save(question);
        return new ResponseEntity<> ("success",HttpStatus.CREATED);
    }
    public String deleteQuestion(int questionId) {
        questionDao.deleteById(questionId);
        return "Successfully deleted";
    }
}
