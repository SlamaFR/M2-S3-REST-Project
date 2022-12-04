package com.kamelia.ebc.common.base;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public final class BikeOrder implements Serializable {

    private final Bike bike;
    private final Instant instant;
    private final String comment;
    private final ReturnState state;


    public BikeOrder(Bike bike, Instant instant, String comment, ReturnState state) {
        Objects.requireNonNull(bike);
        Objects.requireNonNull(instant);
        Objects.requireNonNull(comment);
        Objects.requireNonNull(state);
        this.bike = bike;
        this.instant = instant;
        this.comment = comment;
        this.state = state;
    }

    public Bike bike() {
        return bike;
    }

    public Instant instant() {
        return instant;
    }

    public String comment() {
        return comment;
    }

    public ReturnState state() {
        return state;
    }
}
