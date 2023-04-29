package com.example.multithreadcodesample.domain.asset.model.entity;

import com.example.multithreadcodesample.domain.brand.model.entity.Brand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "asset")
public class Asset {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private int price;

    private boolean isArchived;
}