package com.kamelia.ebc.common;

import java.io.Serializable;
import java.util.Objects;

public record BikeOrder(Bike bike, Comment comment, ReturnState state) implements Serializable {

    public BikeOrder {
        Objects.requireNonNull(bike);
        Objects.requireNonNull(comment);
        Objects.requireNonNull(state);
    }

}
