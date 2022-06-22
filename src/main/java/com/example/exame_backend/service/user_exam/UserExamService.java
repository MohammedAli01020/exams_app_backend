package com.example.exame_backend.service.user_exam;


import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.UserDetails;
import com.example.exame_backend.model.persisitece.UserExam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserExamService {

    UserExam save(UserExam userExam);

    List<UserExam> all();

    List<UserExam> allByUserId(UserDetails userDetails);

    Page<UserExam> getPageUserExams(Pageable pageable);

    UserExam getUserExamByUserAndExam(UserDetails userDetails, Exam exam);

}
