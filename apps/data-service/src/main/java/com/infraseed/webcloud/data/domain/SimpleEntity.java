package com.infraseed.webcloud.data.domain;

import com.infraseed.webcloud.common.data.TenantScoped;
import com.infraseed.webcloud.common.data.TenantScopedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Simple entity with tenant access control: extends TenantScopedEntity (inherits tenant_id)
 * and is annotated @TenantScoped so tenant is auto-injected. Only extra attribute is cnt.
 */
@Entity
@Table(name = "simple_entity")
@TenantScoped
@EntityListeners(TenantInjectingListener.class)
public class SimpleEntity extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnt", nullable = false)
    private int cnt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
