package com.infraseed.webcloud.data.service;

import com.infraseed.webcloud.common.core.ValiRet;
import com.infraseed.webcloud.common.data.BaseOp;
import com.infraseed.webcloud.common.security.TenantContext;
import com.infraseed.webcloud.data.domain.SimpleEntity;
import com.infraseed.webcloud.data.repo.SimpleEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SimpleEntityService implements BaseOp<SimpleEntity> {

    private final SimpleEntityRepository repository;

    public SimpleEntityService(SimpleEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public ValiRet validate(SimpleEntity data) {
        if (data == null) {
            return ValiRet.fail(40001, "body required");
        }
        return ValiRet.pass();
    }

    @Override
    @Transactional
    public SimpleEntity update(SimpleEntity old, SimpleEntity neu) {
        old.setCnt(neu.getCnt());
        return repository.save(old);
    }

    @Override
    @Transactional
    public SimpleEntity insert(SimpleEntity data) {
        return repository.save(data);
    }

    @Override
    @Transactional
    public SimpleEntity remove(SimpleEntity data) {
        repository.softDeleteById(data.getId());
        data.setDeleted(true);
        return data;
    }

    @Override
    public Page<SimpleEntity> queryList(Pageable pageable) {
        String tenantId = TenantContext.getTenantId();
        if (tenantId != null && !tenantId.isBlank()) {
            return repository.findByTenantId(tenantId, pageable);
        }
        return repository.findAllNotDeleted(pageable);
    }
}
