package com.example.exame_backend.model.requests;

import com.example.exame_backend.model.persisitece.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamCreateRequest {


    private String examTitle;

    private Collection<Question> questions;

    private Long creationDateTime;

    private Long userId;

}
