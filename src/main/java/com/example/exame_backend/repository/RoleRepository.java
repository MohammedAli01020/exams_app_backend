package com.example.exame_backend.repository;

import com.example.exame_backend.model.persisitece.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
