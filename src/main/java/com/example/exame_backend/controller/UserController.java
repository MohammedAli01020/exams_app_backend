package com.example.exame_backend.controller;


import com.example.exame_backend.model.requests.RequestUserDetails;
import com.example.exame_backend.model.persisitece.Role;
import com.example.exame_backend.model.persisitece.UserDetails;
import com.example.exame_backend.repository.RoleRepository;
import com.example.exame_backend.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    final UserService userService;

    final RoleRepository roleRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {

        return  new ResponseEntity<>("welcome", HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        return  new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity<UserDetails> createUser(@RequestBody RequestUserDetails userDetails) {


        UserDetails user = new UserDetails();


        user.setUsername(userDetails.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        user.setAge(userDetails.getAge());


        Role role = roleRepository.save(new Role(userDetails.getRole()));

        user.setRoles(Collections.singleton(role));

        userService.saveUser(user);


        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping("/updatePassword/{uid}")
    public ResponseEntity<UserDetails> updatePassword(@PathVariable Long uid,
                                                      String newPassword) throws Exception {

        UserDetails userDetails = userService.getUserById(uid);

        if (userDetails != null) {
            userDetails.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return new ResponseEntity<>(userService.saveUser(userDetails), HttpStatus.OK);
        } else {

            throw new Exception();
        }

    }



    @PutMapping("/updatePasswordByUsername/{username}")
    public ResponseEntity<UserDetails> updatePasswordByUsername(@PathVariable String username,
                                                      String newPassword) throws Exception {

        UserDetails userDetails = userService.getUserByUsername(username);

        if (userDetails != null) {
            userDetails.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return new ResponseEntity<>(userService.saveUser(userDetails), HttpStatus.OK);
        } else {

            throw new Exception();
        }

    }



    @PutMapping("/updateUsername/{uid}")
    public ResponseEntity<UserDetails> updateUsername(@PathVariable Long uid,
                                                      String newUsername) throws Exception {

        UserDetails userDetails = userService.getUserById(uid);

        if (userDetails != null) {
            userDetails.setUsername(newUsername);
            return new ResponseEntity<>(userService.saveUser(userDetails), HttpStatus.OK);
        } else {

            throw new Exception();
        }

    }


    @GetMapping("/user/{username}")
    public ResponseEntity<UserDetails> findUserByUsername(@PathVariable  String username) {


        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);

    }


    @GetMapping("/userId/{userId}")
    public ResponseEntity<UserDetails> findUserByUId(@PathVariable  Long userId) {

        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);

    }











}
