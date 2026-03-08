package com.infraseed.webcloud.common.data;

import com.infraseed.webcloud.common.core.ValiRet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Common operation contract for tenant-aware CRUD. Validation returns code 0 for pass.
 */
public interface BaseOp<T> {

    ValiRet validate(T data);

    T update(T old, T neu);

    T insert(T data);

    T remove(T data);

    Page<T> queryList(Pageable pageable);
}
