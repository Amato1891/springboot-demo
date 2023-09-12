package com.amato.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
    public class HighScores {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Getter
        private Integer quizId;
        @Getter
        private Integer totalQuestions;
        @Getter
        private Integer correctAnswers;
        @Getter
        private String name;
        @Getter
        private String category;
        @Getter
        private Integer score;

    public void setQuizId(Integer quizId) {
            this.quizId = quizId;
        }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setTotalQuestions(Integer totalQuestions) {
            this.totalQuestions = totalQuestions;
        }

    public void setCorrectAnswers(Integer correctAnswers) {
            this.correctAnswers = correctAnswers;
        }
    public void setCategory(String category) {
        this.category = category;
    }
    }
