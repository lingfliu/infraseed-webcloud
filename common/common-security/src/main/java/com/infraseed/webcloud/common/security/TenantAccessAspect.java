package com.infraseed.webcloud.common.security;

import com.infraseed.webcloud.common.core.ErrorCodes;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspect that enforces tenant context when @TenantAccess is present. Rejects if tenant not set.
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class TenantAccessAspect {

    private static final Logger log = LoggerFactory.getLogger(TenantAccessAspect.class);

    @Around("@annotation(com.infraseed.webcloud.common.security.TenantAccess) || @within(com.infraseed.webcloud.common.security.TenantAccess)")
    public Object enforceTenant(ProceedingJoinPoint pjp) throws Throwable {
        String tenantId = TenantContext.getTenantId();
        if (tenantId == null || tenantId.isBlank()) {
            log.warn("TenantAccess required but tenant context missing");
            throw new TenantRequiredException(ErrorCodes.FORBIDDEN, "Tenant context required");
        }
        return pjp.proceed();
    }
}
