package com.example.exame_backend.model.persisitece;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_exams")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserExam {


    @EmbeddedId
    private UserExamId userExamId;

    @MapsId("examId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Exam exam;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserDetails userDetails;

    @Column(name = "score")
    private Long score;

    @Column(name = "full_score")
    private Long fullScore;


    @Column(name = "correct")
    private Long correct;

    @Column(name = "incorrect")
    private Long incorrect;

    @Column(name = "not_attempted")
    private Long notAttempted;


    @Column(name = "submitted_date")
    private Long submittedDate;

}
