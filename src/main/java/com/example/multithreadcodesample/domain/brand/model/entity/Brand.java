package com.example.multithreadcodesample.domain.brand.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.StandardException;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "brand")
public class Brand  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int assetNumber;

    private int stylingNumber;

    private int likes;

}
