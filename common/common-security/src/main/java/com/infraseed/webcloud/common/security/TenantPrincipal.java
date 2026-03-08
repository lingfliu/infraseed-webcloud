package com.infraseed.webcloud.common.security;

/**
 * Abstraction for auth principal carrying tenant and user identity.
 * Implement this for your Principal type and set it from your auth filter/server.
 */
public interface TenantPrincipal {

    String getTenantId();

    String getUserId();

    String getUsername();
}
