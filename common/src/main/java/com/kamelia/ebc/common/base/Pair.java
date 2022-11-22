package com.kamelia.ebc.common.base;

import java.io.Serializable;
import java.util.Objects;

public record Pair<T1, T2>(T1 first, T2 second) implements Serializable {

    public Pair {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
    }

}
