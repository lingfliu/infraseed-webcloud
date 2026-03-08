package com.infraseed.webcloud.data.web;

import com.infraseed.webcloud.common.core.ApiResult;
import com.infraseed.webcloud.common.core.ValiResult;
import com.infraseed.webcloud.common.security.TenantAccess;
import com.infraseed.webcloud.common.web.PageDto;
import com.infraseed.webcloud.data.domain.SampleEntity;
import com.infraseed.webcloud.data.domain.SimpleEntity;
import com.infraseed.webcloud.data.service.SampleEntityService;
import com.infraseed.webcloud.data.service.SimpleEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
@Tag(name = "Data", description = "Tenant-aware data operations")
public class DataController {

    private final SampleEntityService sampleEntityService;
    private final SimpleEntityService simpleEntityService;

    public DataController(SampleEntityService sampleEntityService, SimpleEntityService simpleEntityService) {
        this.sampleEntityService = sampleEntityService;
        this.simpleEntityService = simpleEntityService;
    }

    @GetMapping("/samples")
    @TenantAccess
    @Operation(summary = "List samples")
    public ResponseEntity<ApiResult<PageDto<SampleEntity>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        var p = sampleEntityService.queryList(PageRequest.of(page, size));
        return ResponseEntity.ok(ApiResult.ok(PageDto.of(p)));
    }

    @PostMapping("/samples")
    @TenantAccess
    @Operation(summary = "Create sample")
    public ResponseEntity<ApiResult<SampleEntity>> create(@RequestBody SampleEntity body) {
        ValiResult v = sampleEntityService.validate(body);
        if (!v.isPassed()) {
            return ResponseEntity.badRequest().body(ApiResult.err(v.code(), v.msg()));
        }
        SampleEntity created = sampleEntityService.insert(body);
        return ResponseEntity.ok(ApiResult.ok(created));
    }

    @GetMapping("/simple")
    @TenantAccess
    @Operation(summary = "List simple entities")
    public ResponseEntity<ApiResult<PageDto<SimpleEntity>>> listSimple(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        var p = simpleEntityService.queryList(PageRequest.of(page, size));
        return ResponseEntity.ok(ApiResult.ok(PageDto.of(p)));
    }

    @PostMapping("/simple")
    @TenantAccess
    @Operation(summary = "Create simple entity")
    public ResponseEntity<ApiResult<SimpleEntity>> createSimple(@RequestBody SimpleEntity body) {
        ValiResult v = simpleEntityService.validate(body);
        if (!v.isPassed()) {
            return ResponseEntity.badRequest().body(ApiResult.err(v.code(), v.msg()));
        }
        SimpleEntity created = simpleEntityService.insert(body);
        return ResponseEntity.ok(ApiResult.ok(created));
    }
}
