package com.example.exame_backend.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDetails {


    private Long userId;

    private String username;

    private String password;

    private int age;

    private String role;


    public RequestUserDetails(String username, String password, int age, String role) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }
}
