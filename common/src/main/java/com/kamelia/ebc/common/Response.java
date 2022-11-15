package com.kamelia.ebc.common;

import java.io.Serializable;
import java.util.Objects;

public record Response<E>(State state, String message, RemoteOptional<E> body) implements Serializable {

    public Response {
        Objects.requireNonNull(state);
        Objects.requireNonNull(message);
        Objects.requireNonNull(body);
    }

    public static <E> Response<E> ok(E body) {
        return new Response<>(State.OK, "OK", RemoteOptional.ofNullable(body));
    }

    public static <E> Response<E> notFound() {
        return new Response<>(State.NOT_FOUND, "Not Found", RemoteOptional.empty());
    }

    public static <E> Response<E> unauthorized() {
        return new Response<>(State.UNAUTHORIZED, "Unauthorized", RemoteOptional.empty());
    }

    public static <E> Response<E> forbidden() {
        return new Response<>(State.FORBIDDEN, "Forbidden", RemoteOptional.empty());
    }

    public static <E> Response<E> badRequest() {
        return new Response<>(State.BAD_REQUEST, "Bad Request", RemoteOptional.empty());
    }

    public enum State {
        OK,
        NOT_FOUND,
        UNAUTHORIZED,
        FORBIDDEN,
        BAD_REQUEST,
    }

}
