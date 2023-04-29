package com.example.multithreadcodesample.domain.like.model.entity;

import com.example.multithreadcodesample.common.base.BaseEntity;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Like<E extends BaseEntity> extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @Column(name = "target_id")
    private E target;
}
