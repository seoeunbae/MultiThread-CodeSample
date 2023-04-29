package com.example.multithreadcodesample.domain.asset.model.entity;

import com.example.multithreadcodesample.common.base.BaseEntity;
import com.example.multithreadcodesample.common.base.LikeBaseEntity;
import com.example.multithreadcodesample.domain.brand.model.entity.Brand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "asset")
public class Asset extends LikeBaseEntity {
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column
    private int price;

    @Column(name = "is_archived")
    private boolean isArchived;
}
