package com.example.exame_backend.service.question;



import com.example.exame_backend.model.persisitece.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {


    Question saveQuestion(Question question);
    void removeQuestion(Long questionId);
    Question getQuestionById(Long id);

    Page<Question> getPageQuestionsByExamId(Long examId, Pageable pageable);


    List<Question> getAllQuestionsByExamId(Long examId);

}
