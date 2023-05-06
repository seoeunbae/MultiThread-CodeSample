package com.example.multithreadcodesample.domain.following.model.entity;

import com.example.multithreadcodesample.common.base.BaseEntity;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Following extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    public Following(User user, User followingUser){
        this.user = user;
        this.followingUser = followingUser;
    }
}
