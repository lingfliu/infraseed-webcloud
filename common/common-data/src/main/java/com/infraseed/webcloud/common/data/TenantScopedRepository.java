package com.infraseed.webcloud.common.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

/**
 * Repository for entities that are tenant-scoped ({@link TenantScopedEntity}). Adds
 * findByTenantId for tenant-filtered listing.
 */
@NoRepositoryBean
public interface TenantScopedRepository<T extends TenantScopedEntity, ID> extends SoftDeleteRepository<T, ID> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.tenantId = :tenantId AND e.deleted = false")
    Page<T> findByTenantId(@Param("tenantId") String tenantId, Pageable pageable);
}
