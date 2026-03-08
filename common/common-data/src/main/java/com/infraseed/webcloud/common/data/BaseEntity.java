package com.infraseed.webcloud.common.data;

import com.infraseed.webcloud.common.core.MetadataPrimitives;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

/**
 * Base entity with only core data attributes: uuid, created_at, updated_at, is_deleted.
 * For tenant access-controlled data, extend {@link TenantScopedEntity} and use {@link TenantScoped}.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    private String uuid = UUID.randomUUID().toString();

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    @PrePersist
    protected void onPrePersist() {
        if (uuid == null || uuid.isBlank()) {
            uuid = MetadataPrimitives.newUuid();
        }
        if (createdAt == null) {
            createdAt = MetadataPrimitives.now();
        }
        if (updatedAt == null) {
            updatedAt = MetadataPrimitives.now();
        }
    }

    @PreUpdate
    protected void onPreUpdate() {
        updatedAt = MetadataPrimitives.now();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
