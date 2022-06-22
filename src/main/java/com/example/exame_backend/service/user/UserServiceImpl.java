package com.example.exame_backend.service.user;


import com.example.exame_backend.model.persisitece.UserDetails;
import com.example.exame_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails saveUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    @Override
    public List<UserDetails> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails getUserById(Long uid) {
        return userRepository.findByUserId(uid);
    }


    @Override
    public UserDetails getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
