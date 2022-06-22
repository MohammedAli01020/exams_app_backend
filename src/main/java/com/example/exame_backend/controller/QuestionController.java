package com.example.exame_backend.controller;

import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.Question;
import com.example.exame_backend.model.requests.ExamPage;
import com.example.exame_backend.model.requests.QuestionRequest;
import com.example.exame_backend.service.exam.ExamService;
import com.example.exame_backend.service.question.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {


    final QuestionService questionService;
    final ExamService examService;


    public QuestionController(QuestionService questionService, ExamService examService) {
        this.questionService = questionService;
        this.examService = examService;
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Question> addQuestionToExam(@PathVariable  Long id,
                                                      @RequestBody QuestionRequest questionRequest) throws Exception {
        Exam exam = examService.getExamById(id);



        if (exam != null) {

            Question q = new Question();
            q.setTitle(questionRequest.getTitle());
            q.setExam(exam);
            q.setCreationDateTime(questionRequest.getCreationDateTime());

            q.setAnswers(questionRequest.getAnswers());
            q.setCorrectAnswer(questionRequest.getCorrectAnswer());


            return new ResponseEntity<>(questionService.saveQuestion(q),
                    HttpStatus.OK);
        } else {

            throw new Exception();
        }

    }


    @DeleteMapping("/delete/{id}")
    public void  deleteExam(@PathVariable Long id) {

        Question question = questionService.getQuestionById(id);

        if (question != null) {
            questionService.removeQuestion(id);
        }

    }

    @GetMapping("/page")
    public ResponseEntity<Page<Question>> getPageQuestionsByExam(Long examId, ExamPage examPage) {
        return new ResponseEntity<>( questionService.getPageQuestionsByExamId(examId, getPageable(examPage)), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestionsByExam(Long examId) {
        return new ResponseEntity<>( questionService.getAllQuestionsByExamId(examId), HttpStatus.OK);
    }

    private Pageable getPageable(ExamPage examPage) {

        Sort sort = Sort.by(examPage.getSortDirection(), examPage.getSortBy());

        return PageRequest.of(examPage.getPageNumber(), examPage.getPageSize(), sort);

    }

}
