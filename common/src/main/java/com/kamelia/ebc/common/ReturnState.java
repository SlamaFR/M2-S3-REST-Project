package com.kamelia.ebc.common;

import java.io.Serializable;
import java.util.Objects;

public record ReturnState(String details, State state) implements Serializable {

    public ReturnState {
        Objects.requireNonNull(details);
        Objects.requireNonNull(state);
    }

    public enum State {
        AS_NEW,
        GOOD,
        SLIGHTLY_DAMAGED,
        BAD,
        BROKEN,
        LOST
    }

}
