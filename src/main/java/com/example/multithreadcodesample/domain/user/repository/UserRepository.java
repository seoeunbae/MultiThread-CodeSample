package com.example.multithreadcodesample.domain.user.repository;

import com.example.multithreadcodesample.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findUserById(Long id);
}
