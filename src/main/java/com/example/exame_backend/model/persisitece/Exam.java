package com.example.exame_backend.model.persisitece;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "exams ")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @Id
    @GeneratedValue
    @Column(name = "exam_id")
    private Long examId;


    @Column(name = "exam_title")
    private String examTitle;

    @OneToMany(mappedBy = "exam")
    private Collection<Question> questions;


    @Column(name = "creation_date_time")
    private Long creationDateTime;


    @ManyToOne
    @JoinColumn(name = "u_fk", referencedColumnName = "u_id")
    private UserDetails userDetails;


    @OneToMany(
            mappedBy = "exam",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Collection<UserExam> userExamList;

    public Exam(String examTitle, Collection<Question> questions, Long creationDateTime, UserDetails userDetails) {
        this.examTitle = examTitle;
        this.questions = questions;
        this.creationDateTime = creationDateTime;
        this.userDetails = userDetails;
    }


    public Long getExamId() {
        return examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    @JsonIgnore
    public Collection<Question> getQuestions() {
        return questions;
    }

    public Long getCreationDateTime() {
        return creationDateTime;
    }


    public UserDetails getUserDetails() {
        return userDetails;
    }

    @JsonIgnore
    public Collection<UserExam> getUserExamList() {
        return userExamList;
    }
}
