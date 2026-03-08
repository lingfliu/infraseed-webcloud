package com.infraseed.webcloud.common.core;

import java.util.Optional;

/**
 * Generic API response wrapper.
 */
public record ApiResponse<T>(int code, String msg, T data) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(ValiRet.PASS, null, data);
    }

    public static <T> ApiResponse<T> fail(int code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }

    public Optional<T> getData() {
        return Optional.ofNullable(data);
    }
}
