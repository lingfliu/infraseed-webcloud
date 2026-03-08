package com.infraseed.webcloud.common.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Base repository with soft-delete support. Use softDeleteById for logical delete.
 */
@NoRepositoryBean
public interface SoftDeleteRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

    default Optional<T> softDeleteById(ID id) {
        return findById(id).map(entity -> {
            entity.setDeleted(true);
            return save(entity);
        });
    }

    @Query("SELECT e FROM #{#entityName} e WHERE e.uuid = :uuid AND e.deleted = false")
    Optional<T> findByUuidAndDeletedFalse(@Param("uuid") String uuid);

    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    Page<T> findAllNotDeleted(Pageable pageable);
}
