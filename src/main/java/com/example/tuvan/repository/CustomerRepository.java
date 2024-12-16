package com.example.tuvan.repository;

import com.example.tuvan.entity.Customer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select t from Customer t where t.status = 1")
    List<Customer> getAllIfActive();

    @Query("select t from Customer t")
    List<Customer> getAll();

    Long countByStatus(Integer status);
    Customer findByUserEmail(String email);



}
