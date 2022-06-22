package com.example.exame_backend.service.user;

import com.example.exame_backend.model.persisitece.UserDetails;

import java.util.List;

public interface UserService {

    UserDetails saveUser(UserDetails userDetails);

    List<UserDetails> getUsers();

    UserDetails getUserById(Long uid);

    UserDetails getUserByUsername(String username);


}
