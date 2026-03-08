package com.infraseed.webcloud.common.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an entity as tenant access-controlled. When present, the entity should extend
 * {@link TenantScopedEntity} so that tenant_id is stored and auto-injected from context.
 * Use this for data that must be scoped by tenant; omit for data that has no tenant.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TenantScoped {
}
