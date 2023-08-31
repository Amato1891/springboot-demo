package com.amato.quizapp.dao;
import com.amato.quizapp.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TaskDao extends JpaRepository<Task, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.reminder = NOT t.reminder WHERE t.id = :taskId")
    void updateReminderById(int taskId);
}
