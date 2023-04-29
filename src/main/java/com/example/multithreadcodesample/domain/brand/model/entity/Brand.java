package com.example.multithreadcodesample.domain.brand.model.entity;

import com.example.multithreadcodesample.common.base.BaseEntity;
import com.example.multithreadcodesample.common.base.LikeBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.StandardException;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Brand extends LikeBaseEntity {
    @Column
    private String name;
    @Column(name = "asset_number")
    private int assetNumber;
    @Column(name = "styling_number")
    private int stylingNumber;


}
