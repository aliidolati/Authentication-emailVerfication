package com.example.authentication.repository;

import com.example.authentication.Model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username) ;
    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.enabled = TRUE where u.email = ?1")
    void enaleUser(String email) ;
}
