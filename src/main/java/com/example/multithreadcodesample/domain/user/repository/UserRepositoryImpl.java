package com.example.multithreadcodesample.domain.user.repository;

import com.example.multithreadcodesample.common.QueryDslSupport;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
@Repository
public class UserRepositoryImpl extends QueryDslSupport implements UserRepositoryCustom{
    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
