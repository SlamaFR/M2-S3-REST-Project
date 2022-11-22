package com.kamelia.ebc.common.base;

import java.io.Serializable;
import java.util.Objects;

public record Comment(String content, Stars stars) implements Serializable {

    public Comment {
        Objects.requireNonNull(content);
        Objects.requireNonNull(stars);
    }

    public enum Stars {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }

}
