package com.example.exame_backend.service.exam;

import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface  ExamService {

    Exam saveExam(Exam exam);


    void deleteExam(Long id);


    Exam getExamById(Long id);

    Page<Exam> getAllExam(Pageable pageable);

}
