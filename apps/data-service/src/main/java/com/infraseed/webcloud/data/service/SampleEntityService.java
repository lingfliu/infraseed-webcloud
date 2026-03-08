package com.infraseed.webcloud.data.service;

import com.infraseed.webcloud.common.core.ValiRet;
import com.infraseed.webcloud.common.data.BaseOp;
import com.infraseed.webcloud.data.domain.SampleEntity;
import com.infraseed.webcloud.data.repo.SampleEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SampleEntityService implements BaseOp<SampleEntity> {

    private final SampleEntityRepository repository;

    public SampleEntityService(SampleEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public ValiRet validate(SampleEntity data) {
        if (data == null || data.getName() == null || data.getName().isBlank()) {
            return ValiRet.fail(40001, "name required");
        }
        return ValiRet.pass();
    }

    @Override
    @Transactional
    public SampleEntity update(SampleEntity old, SampleEntity neu) {
        if (neu.getName() != null) {
            old.setName(neu.getName());
        }
        return repository.save(old);
    }

    @Override
    @Transactional
    public SampleEntity insert(SampleEntity data) {
        return repository.save(data);
    }

    @Override
    @Transactional
    public SampleEntity remove(SampleEntity data) {
        repository.softDeleteById(data.getId());
        data.setDeleted(true);
        return data;
    }

    @Override
    public Page<SampleEntity> queryList(Pageable pageable) {
        return repository.findAllNotDeleted(pageable);
    }
}
