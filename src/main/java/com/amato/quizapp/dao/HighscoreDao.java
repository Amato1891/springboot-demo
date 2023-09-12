package com.amato.quizapp.dao;
import com.amato.quizapp.model.HighScores;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HighscoreDao extends JpaRepository<HighScores, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE HighScores hs SET hs.name = :newName WHERE hs.quizId = :quizId")
    void updateNameByQuizId(@Param("quizId") int quizId, @Param("newName") String newName);
}
