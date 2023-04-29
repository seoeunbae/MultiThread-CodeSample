package com.example.multithreadcodesample.common.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

public class LikeBaseEntity extends BaseEntity{
    @Column(name = "like_number")
    private int likeNumber;
}
