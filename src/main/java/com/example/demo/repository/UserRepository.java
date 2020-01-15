package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email = '?1'")
    public User findByEmail(String email);
    public List<User> findAllByStatus(Boolean status);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE full_name LIKE %?1%")
    public Page<User> searchUser(String keyword, Pageable pageable);
}
