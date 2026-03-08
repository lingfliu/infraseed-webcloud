package com.infraseed.webcloud.data.web;

import com.infraseed.webcloud.common.security.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Sets tenant context from X-Tenant-Id header for local/dev. Replace with JWT or auth server claims in production.
 */
@Component
@Order(1)
public class TenantContextFilter extends OncePerRequestFilter {

    private static final String HEADER_TENANT = "X-Tenant-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String tenantId = request.getHeader(HEADER_TENANT);
            if (tenantId != null && !tenantId.isBlank()) {
                TenantContext.setTenantId(tenantId.trim());
            }
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}
