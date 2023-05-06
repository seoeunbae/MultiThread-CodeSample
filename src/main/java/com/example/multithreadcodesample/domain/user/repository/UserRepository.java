package com.example.multithreadcodesample.domain.user.repository;

import com.example.multithreadcodesample.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
