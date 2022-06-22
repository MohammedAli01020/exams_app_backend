package com.example.exame_backend.service.user_exam;



import com.example.exame_backend.model.persisitece.Exam;
import com.example.exame_backend.model.persisitece.UserDetails;
import com.example.exame_backend.model.persisitece.UserExam;
import com.example.exame_backend.repository.UserExamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExamServiceImpl implements UserExamService{
    final UserExamRepository userExamRepository;



    public UserExamServiceImpl(UserExamRepository userExamRepository) {
        this.userExamRepository = userExamRepository;

    }

    @Override
    public UserExam save(UserExam userExam) {

        return userExamRepository.save(userExam);


    }

    @Override
    public List<UserExam> all() {
        return userExamRepository.findAll();
    }

    @Override
    public List<UserExam> allByUserId(UserDetails userDetails) {
        return userExamRepository.findByUserDetails(userDetails);
    }

    @Override
    public Page<UserExam> getPageUserExams(Pageable pageable) {
        return userExamRepository.findAll(pageable);
    }

    @Override
    public UserExam getUserExamByUserAndExam(UserDetails userDetails, Exam exam) {
        return userExamRepository.findByUserDetailsAndExam(userDetails, exam);
    }
}
