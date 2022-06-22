package com.example.exame_backend.repository;

import com.example.exame_backend.model.persisitece.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserDetails, Long> {

    UserDetails findByUsername(String userName);

    UserDetails findByUserId(Long userId);

}
