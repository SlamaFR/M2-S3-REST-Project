package com.kamelia.ebc.common.base;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public record BikeOrder(
    Bike bike,
    User author,
    Instant instant,
    Comment comment,
    ReturnState state
) implements Serializable {

    public BikeOrder {
        Objects.requireNonNull(bike);
        Objects.requireNonNull(author);
        Objects.requireNonNull(instant);
        Objects.requireNonNull(comment);
        Objects.requireNonNull(state);
    }

}