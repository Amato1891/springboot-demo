package com.amato.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue
    private Integer id;
    private String test_category;
    private String date_created;
    private String time_created;
    @ManyToMany
    private List<Question> questions;
}
