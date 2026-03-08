package com.infraseed.webcloud.common.core;

import java.time.Instant;

/**
 * Primitives for entity metadata: uuid, created_at, updated_at, is_deleted.
 */
public final class MetadataPrimitives {
    private MetadataPrimitives() {}

    public static String newUuid() {
        return java.util.UUID.randomUUID().toString();
    }

    public static Instant now() {
        return Instant.now();
    }
}
