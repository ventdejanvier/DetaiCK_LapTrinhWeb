package com.example.tuvan.repository;

import com.example.tuvan.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SpringBootApplication
public interface UserRepository extends JpaRepository<User, Long> {

    public User save(User user);

    Optional<User> findByEmailAndStatus(String email, Integer status);

    Optional<User> findByEmail(String email);


    @Modifying
    @Transactional
    @Query("UPDATE User as u set u.password =?1 where u.email=?2")
    int updatePassword(String password, String email);

    @Query("select u.password from User u where u.email=?1")
    String getUsersByUserId(String email);

    boolean existsByEmailAndPassword(String email, String password);


    @Query(value = "SELECT u.id FROM User u " +
            "WHERE u.email = :email"
    )
    Long findIdByEmail(@Param("email") String email);

    @Query("SELECT DISTINCT u FROM User u " +
            "LEFT JOIN FETCH u.roleEntities r " +
            "WHERE r.name = 'ADMIN' OR r.name = 'SUPER_ADMIN'")
    List<User> getAllAdmins();


}
