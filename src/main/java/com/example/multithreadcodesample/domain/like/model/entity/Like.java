package com.example.multithreadcodesample.domain.like.model.entity;

import com.example.multithreadcodesample.common.base.BaseEntity;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.TargetType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "likes")
@NoArgsConstructor
public class Like extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "target_id")
    private long targetId;

    @Column(name = "target_type")
    private TargetType targetType;

    public Like(User user, long targetId, TargetType targetType) {
        this.user = user;
        this.targetId = targetId;
        this.targetType = targetType;
    }

    public enum TargetType{
        ASSET, STYLING, BRAND
    }



}
