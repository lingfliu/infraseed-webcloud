package com.infraseed.webcloud.common.web;

import com.infraseed.webcloud.common.core.ApiResult;
import com.infraseed.webcloud.common.core.ErrorCodes;
import com.infraseed.webcloud.common.security.TenantRequiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handling for REST APIs. Maps exceptions to ApiResult and HTTP status.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TenantRequiredException.class)
    public ResponseEntity<ApiResult<Void>> handleTenantRequired(TenantRequiredException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResult.err(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResult<Void>> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResult.err(ErrorCodes.VALIDATION_FAILED, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<Void>> handleGeneric(Exception e) {
        log.error("Unhandled exception", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResult.err(ErrorCodes.INTERNAL_ERROR, "Internal server error"));
    }
}
