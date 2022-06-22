package com.example.exame_backend.controller;

import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.UserDetails;
import com.example.exame_backend.model.persisitece.UserExam;
import com.example.exame_backend.model.persisitece.UserExamId;
import com.example.exame_backend.model.requests.UserExamCreateRequest;
import com.example.exame_backend.model.requests.UserExamPage;
import com.example.exame_backend.service.exam.ExamService;
import com.example.exame_backend.service.user.UserService;
import com.example.exame_backend.service.user_exam.UserExamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userExam")
public class UserExamController {


    final UserExamService userExamService;
    final UserService userService;
    final ExamService examService;

    public UserExamController(UserExamService userExamService, UserService userService, ExamService examService) {
        this.userExamService = userExamService;
        this.userService = userService;
        this.examService = examService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserExam> createUserExam(@RequestBody UserExamCreateRequest userExamCreateRequest) throws Exception {


        UserExam userExam = new UserExam();


        UserDetails userDetails = userService.getUserById(userExamCreateRequest.getUserId());
        Exam exam = examService.getExamById(userExamCreateRequest.getExamId());


        if (userDetails == null || exam == null) {
            throw new Exception();
        } else {


            userExam.setExam(exam);
            userExam.setUserDetails(userDetails);

            userExam.setUserExamId(new UserExamId(userExamCreateRequest.getUserId(), userExamCreateRequest.getExamId()));
            userExam.setScore(userExamCreateRequest.getScore());


            userExam.setFullScore(userExamCreateRequest.getFullScore());
            userExam.setCorrect(userExamCreateRequest.getCorrect());
            userExam.setIncorrect(userExamCreateRequest.getIncorrect());
            userExam.setSubmittedDate(userExamCreateRequest.getSubmittedDate());
            userExam.setNotAttempted(userExamCreateRequest.getNotAttempted());


            return new ResponseEntity<>(userExamService.save(userExam), HttpStatus.OK);
        }


    }


    @GetMapping("/all")
    public ResponseEntity<List<UserExam>> getAllUserExams() {
        return new ResponseEntity<>(userExamService.all(), HttpStatus.OK);
    }


    @GetMapping("/allByUserId/{userId}")
    public ResponseEntity<List<UserExam>> getAllUserExamsByUserId(@PathVariable Long userId) throws Exception {

        UserDetails userDetails = userService.getUserById(userId);

        if (userDetails == null) {
            throw new Exception();
        } else {
            return new ResponseEntity<>(userExamService.allByUserId(userDetails), HttpStatus.OK);
        }

    }

    @GetMapping("/byUserAndExam/")
    public ResponseEntity<UserExam> getUserExamByUserAndExam(@RequestParam Long userId,
                                                             @RequestParam Long examId) throws Exception {


        UserDetails userDetails = userService.getUserById(userId);
        Exam exam = examService.getExamById(examId);

        if (userDetails == null || exam == null) {
            throw new Exception();
        } else {
            return new ResponseEntity<>(userExamService.getUserExamByUserAndExam(userDetails, exam),
                    HttpStatus.OK);

        }


    }


    @GetMapping("/page")
    public ResponseEntity<Page<UserExam>> getPageUserExams(UserExamPage userExamPage) {

        return new ResponseEntity<>(userExamService.getPageUserExams(getPageable(userExamPage)), HttpStatus.OK);
    }


    private Pageable getPageable(UserExamPage userExamPage) {

        Sort sort = Sort.by(userExamPage.getSortDirection(), userExamPage.getSortBy());

        return PageRequest.of(userExamPage.getPageNumber(), userExamPage.getPageSize(), sort);

    }
}
