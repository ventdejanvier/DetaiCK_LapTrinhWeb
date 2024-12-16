package com.example.tuvan.repository;

import com.example.tuvan.entity.Question;
import com.example.tuvan.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SpringBootApplication
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDepartmentId(Long departmentId);
}
