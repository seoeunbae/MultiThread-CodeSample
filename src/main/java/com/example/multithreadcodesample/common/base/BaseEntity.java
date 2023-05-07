package com.example.multithreadcodesample.common.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || Hibernate.getClass(o) != Hibernate.getClass(this)) return false;
        BaseEntity entity = (BaseEntity) o;

        return entity.id == this.id && Objects.equals(createdAt, entity.createdAt);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,createdAt);
    }
}
