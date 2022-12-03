package com.kamelia.ebc.common.base;

public enum ReturnState {
    AS_NEW(100, "as new"),
    GOOD(80, "good"),
    SLIGHTLY_DAMAGED(60, "slightly damaged"),
    BAD(40, "bad"),
    BROKEN(20, "broken"),
    LOST(0, "lost");

    private final int value;
    private final String asString;

    ReturnState(int value, String asString) {
        this.value = value;
        this.asString = asString;
    }

    public static ReturnState fromString(String returnState) {
        for (ReturnState state : ReturnState.values()) {
            if (state.asString.equals(returnState)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown return state: " + returnState);
    }

    public int value() {
        return value;
    }

    public String asString() {
        return asString;
    }
}
