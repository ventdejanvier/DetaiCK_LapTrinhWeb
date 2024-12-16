package com.example.tuvan.repository;

import com.example.tuvan.entity.Consultant;
import com.example.tuvan.entity.Customer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    Consultant findByUserEmail(String email);
}
