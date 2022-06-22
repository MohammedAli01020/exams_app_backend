package com.example.exame_backend.service.exam;

import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.repository.ExamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ExamServiceImpl implements ExamService{

    final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Exam> getAllExam(Pageable pageable) {
        return examRepository.findAll(pageable);
    }
}
