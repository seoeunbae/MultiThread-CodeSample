package com.example.multithreadcodesample.domain.styling.model.entity;

import com.example.multithreadcodesample.common.base.BaseEntity;
import com.example.multithreadcodesample.domain.brand.model.entity.Brand;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
public class Styling extends BaseEntity {
    @Column(name = "like_number")
    private int likes;
    @ManyToOne
    @Column(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @Column(name = "user_id")
    private User user;
    @Column(length = 300)
    private String text;
    @Column(name = "is_archived")
    private Boolean isArchived;

}
