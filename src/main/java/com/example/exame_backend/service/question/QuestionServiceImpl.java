package com.example.exame_backend.service.question;



import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.Question;
import com.example.exame_backend.repository.ExamRepository;
import com.example.exame_backend.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    final QuestionRepository questionRepository;
    final ExamRepository examRepository;


    public QuestionServiceImpl(QuestionRepository questionRepository, ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.examRepository = examRepository;
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void removeQuestion(Long questionId) {

         questionRepository.deleteById(questionId);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Question> getPageQuestionsByExamId(Long examId, Pageable pageable) {
        Exam exam = examRepository.findById(examId).orElseThrow();

        return questionRepository.findByExam(exam, pageable);
    }

    @Override
    public List<Question> getAllQuestionsByExamId(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow();
        return questionRepository.findAllByExam(exam);
    }
}
