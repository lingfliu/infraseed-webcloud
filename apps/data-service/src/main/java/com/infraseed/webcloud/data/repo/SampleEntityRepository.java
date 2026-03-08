package com.infraseed.webcloud.data.repo;

import com.infraseed.webcloud.common.data.SoftDeleteRepository;
import com.infraseed.webcloud.data.domain.SampleEntity;

public interface SampleEntityRepository extends SoftDeleteRepository<SampleEntity, Long> {
}
