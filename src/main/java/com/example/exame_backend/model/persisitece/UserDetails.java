package com.example.exame_backend.model.persisitece;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users_details ")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "u_id")
    private Long userId;


    @Column(name = "user_name")
    private String username;


    @Column(name = "password")
    private String password;


    @Column(name = "age")
    private int age;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;


    @OneToMany(mappedBy = "userDetails")
    private Collection<Exam> exams;


    @OneToMany(
            mappedBy = "userDetails",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Collection<UserExam> userExamList;




    public UserDetails(String username, String password, int age, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.roles = roles;
    }


    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    @JsonIgnore
    public Collection<Exam> getExams() {
        return exams;
    }


    @JsonIgnore
    public Collection<UserExam> getUserExamList() {
        return userExamList;
    }

}
