package com.kamelia.ebc.common.base;

public enum ReturnState {
    AS_NEW(100),
    GOOD(80),
    SLIGHTLY_DAMAGED(60),
    BAD(40),
    BROKEN(20),
    LOST(0);

    private final int value;

    ReturnState(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
