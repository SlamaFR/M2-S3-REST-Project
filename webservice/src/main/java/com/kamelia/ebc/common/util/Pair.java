package com.kamelia.ebc.common.util;

import java.io.Serializable;
import java.util.Objects;

public final class Pair<T1, T2> implements Serializable {

    private final T1 first;
    private final T2 second;


    public Pair(T1 first, T2 second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        this.first = first;
        this.second = second;
    }

    public T1 first() {
        return first;
    }

    public T2 second() {
        return second;
    }
}
