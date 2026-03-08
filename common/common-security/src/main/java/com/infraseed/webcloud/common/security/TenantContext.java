package com.infraseed.webcloud.common.security;

/**
 * Holder for current tenant id (e.g. from JWT or header). Use with TenantContextFilter or reactive equivalent.
 */
public final class TenantContext {
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

    private TenantContext() {}

    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }

    public static String getTenantId() {
        return TENANT_ID.get();
    }

    public static void clear() {
        TENANT_ID.remove();
    }
}
