package com.example.exame_backend.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    private String title;

    private Collection<String> answers;

    private String correctAnswer;

    private Long creationDateTime;

}
