package com.auction.app.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    protected UUID id;

    @Column(name = "created_at")
    protected Instant createdAt;

    protected Instant updateAt;

    @Column(name = "deleted")
    protected Boolean deleted = false;

    @PrePersist
    protected void onCreate() {
        this.setDeleted(false);
        this.setCreatedAt(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.setUpdateAt(Instant.now());
    }
}
