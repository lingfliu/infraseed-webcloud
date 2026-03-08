package com.infraseed.webcloud.common.core;

/**
 * Validation result for BaseOp operations. code 0 means passed; non-zero maps to msg.
 */
public record ValiResult(int code, String msg) {
    public static final int PASS = 0;

    public static ValiResult pass() {
        return new ValiResult(PASS, null);
    }

    public static ValiResult fail(int code, String msg) {
        return new ValiResult(code, msg);
    }

    public boolean isPassed() {
        return code == PASS;
    }
}
