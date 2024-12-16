package com.example.tuvan.repository;

import com.example.tuvan.entity.Role;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@SpringBootApplication
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);
}
