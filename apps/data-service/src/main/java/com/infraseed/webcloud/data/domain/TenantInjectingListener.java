package com.infraseed.webcloud.data.domain;

import com.infraseed.webcloud.common.data.TenantScopedEntity;
import com.infraseed.webcloud.common.security.TenantContext;
import jakarta.persistence.PrePersist;

/**
 * JPA entity listener that injects tenant id from TenantContext into {@link TenantScopedEntity} on persist.
 * Attach with @EntityListeners(TenantInjectingListener.class) on entities that extend TenantScopedEntity.
 */
public class TenantInjectingListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof TenantScopedEntity tenantEntity) {
            String tenantId = TenantContext.getTenantId();
            if (tenantId != null && !tenantId.isBlank()) {
                tenantEntity.setTenantId(tenantId.trim());
            }
        }
    }
}
