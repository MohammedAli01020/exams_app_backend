package com.example.exame_backend.controller;


import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.UserDetails;
import com.example.exame_backend.model.requests.ExamCreateRequest;
import com.example.exame_backend.model.requests.ExamPage;
import com.example.exame_backend.service.exam.ExamService;
import com.example.exame_backend.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    final ExamService examService;
    final UserService userService;


    public ExamController(ExamService examService, UserService userService) {
        this.examService = examService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Exam> createExam(@RequestBody ExamCreateRequest examCreateRequest) throws Exception {

        UserDetails userDetails = userService.getUserById(examCreateRequest.getUserId());

        if (userDetails == null) {
            throw new Exception();
        }

        Exam exam = new Exam();


        exam.setExamTitle(examCreateRequest.getExamTitle());
        exam.setQuestions(examCreateRequest.getQuestions());
        exam.setCreationDateTime(examCreateRequest.getCreationDateTime());
        exam.setUserDetails(userDetails);

        return new ResponseEntity<>(examService.saveExam(exam), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExam(@PathVariable Long id) {

        Exam exam = examService.getExamById(id);

        if (exam != null) {
            examService.deleteExam(id);
        }

    }


    @GetMapping("/all")
    public ResponseEntity<Page<Exam>> getAllExams(ExamPage examPage) {
        return new ResponseEntity<>( examService.getAllExam(getPageable(examPage)), HttpStatus.OK);
    }



    private Pageable getPageable(ExamPage examPage) {

        Sort sort = Sort.by(examPage.getSortDirection(), examPage.getSortBy());

        return PageRequest.of(examPage.getPageNumber(), examPage.getPageSize(), sort);

    }


    // update exam
}


