package com.infraseed.webcloud.common.web;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Pagination DTO for list responses.
 */
public record PageDto<T>(List<T> content, int page, int size, long totalElements, int totalPages) {

    public static <T> PageDto<T> of(Page<T> page) {
        return new PageDto<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
