package com.infraseed.webcloud.common.core;

/**
 * Validation result for BaseOp operations. code 0 means passed; non-zero maps to msg.
 */
public record ValiRet(int code, String msg) {
    public static final int PASS = 0;

    public static ValiRet pass() {
        return new ValiRet(PASS, null);
    }

    public static ValiRet fail(int code, String msg) {
        return new ValiRet(code, msg);
    }

    public boolean isPassed() {
        return code == PASS;
    }
}
