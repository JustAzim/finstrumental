package com.example.finstrumental.repository;

import com.example.finstrumental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

}
