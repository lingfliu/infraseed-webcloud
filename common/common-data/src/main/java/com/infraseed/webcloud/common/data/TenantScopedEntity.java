package com.infraseed.webcloud.common.data;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Extends {@link BaseEntity} with tenant_id for access control. Use for entities that are
 * tenant-scoped; annotate with {@link TenantScoped}. Tenant id is auto-injected on persist
 * via an entity listener when {@link com.infraseed.webcloud.common.security.TenantContext} is set.
 */
@MappedSuperclass
public abstract class TenantScopedEntity extends BaseEntity {

    @Column(name = "tenant_id", length = 36)
    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
