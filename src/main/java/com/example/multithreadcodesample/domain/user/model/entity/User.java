package com.example.multithreadcodesample.domain.user.model.entity;

import com.example.multithreadcodesample.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class User extends BaseEntity {
    @Column
    private String name;
}
