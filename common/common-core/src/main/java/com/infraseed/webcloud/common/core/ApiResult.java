package com.infraseed.webcloud.common.core;

import java.util.Optional;

/**
 * Generic API response wrapper.
 */
public record ApiResult<T>(int code, String msg, T data) {
    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(ValiResult.PASS, null, data);
    }

    public static <T> ApiResult<T> err(int code, String msg) {
        return new ApiResult<>(code, msg, null);
    }

    public Optional<T> getData() {
        return Optional.ofNullable(data);
    }
}
