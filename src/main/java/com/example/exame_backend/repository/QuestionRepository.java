package com.example.exame_backend.repository;

import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findByExam(Exam exam, Pageable pageable);
    List<Question> findAllByExam(Exam exam);

}
