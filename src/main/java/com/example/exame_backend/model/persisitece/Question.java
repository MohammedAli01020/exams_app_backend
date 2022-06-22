package com.example.exame_backend.model.persisitece;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "questions ")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "title")
    private String title;

    @Column(name = "answers")
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<String> answers;

    @Column(name = "correct_answer")
    private String correctAnswer;


    @Column(name = "creation_date_time")
    private Long creationDateTime;

    @ManyToOne
    @JoinColumn(name = "exam_fk", referencedColumnName = "exam_id")
    private Exam exam;


    public Question(String title, Collection<String> answers, String correctAnswer, Long creationDateTime,  Exam exam) {
        this.title = title;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.creationDateTime = creationDateTime;
        this.exam = exam;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public Collection<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }


    public Long getCreationDateTime() {
        return creationDateTime;
    }

    @JsonIgnore
    public Exam getExam() {
        return exam;
    }
}
