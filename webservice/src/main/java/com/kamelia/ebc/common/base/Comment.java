package com.kamelia.ebc.common.base;

import java.io.Serializable;
import java.util.Objects;

public final class Comment implements Serializable {

    private final String content;
    private final Stars stars;


    public Comment(String content, Stars stars) {
        Objects.requireNonNull(content);
        Objects.requireNonNull(stars);
        this.content = content;
        this.stars = stars;
    }

    public String content() {
        return content;
    }

    public Stars stars() {
        return stars;
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
