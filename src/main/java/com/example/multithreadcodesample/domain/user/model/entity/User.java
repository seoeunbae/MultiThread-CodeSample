package com.example.multithreadcodesample.domain.user.model.entity;

import com.example.multithreadcodesample.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "`user`")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column
    private String name;

    @Column
    private Integer following = 0;

    @Column
    private Integer follower = 0;

}
