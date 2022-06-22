package com.example.exame_backend.repository;

import com.example.exame_backend.model.persisitece.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
