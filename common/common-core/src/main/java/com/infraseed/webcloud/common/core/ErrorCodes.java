package com.infraseed.webcloud.common.core;

/**
 * Standard error codes for validation and operations.
 */
public final class ErrorCodes {
    private ErrorCodes() {}

    public static final int VALIDATION_FAILED = 40001;
    public static final int NOT_FOUND = 40401;
    public static final int UNAUTHORIZED = 40101;
    public static final int FORBIDDEN = 40301;
    public static final int CONFLICT = 40901;
    public static final int INTERNAL_ERROR = 50001;
}
