package com.example.exame_backend.repository;

import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.UserDetails;
import com.example.exame_backend.model.persisitece.UserExam;
import com.example.exame_backend.model.persisitece.UserExamId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserExamRepository extends JpaRepository<UserExam, UserExamId> {


    List<UserExam> findByUserDetails(UserDetails userDetails);

    UserExam findByUserDetailsAndExam(UserDetails userDetails, Exam exam);




}
