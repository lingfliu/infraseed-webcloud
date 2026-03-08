package com.infraseed.webcloud.common.security;

import com.infraseed.webcloud.common.core.ErrorCodes;

/**
 * Thrown when a tenant-scoped operation is attempted without tenant context.
 */
public class TenantRequiredException extends RuntimeException {

    private final int code;

    public TenantRequiredException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
