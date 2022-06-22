package com.example.exame_backend.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserExamCreateRequest {

    private Long userId;
    private Long examId;

    private Long score;

    private Long fullScore;

    private Long correct;

    private Long incorrect;

    private Long notAttempted;

    private Long submittedDate;
}
