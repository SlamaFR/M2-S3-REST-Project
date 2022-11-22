package com.kamelia.ebc.common.base;

import java.io.Serializable;
import java.util.Objects;

public record Response<E>(State state, String message, RemoteOptional<E> body) implements Serializable {

    public Response {
        Objects.requireNonNull(state);
        Objects.requireNonNull(message);
        Objects.requireNonNull(body);
    }

    public static <E> Response<E> ok(E body) {
        return ok("", body);
    }

    public static <E> Response<E> ok(String message, E body) {
        Objects.requireNonNull(message);
        return new Response<>(State.OK, message, RemoteOptional.ofNullable(body));
    }

    public static <E> Response<E> notFound(String message) {
        Objects.requireNonNull(message);
        return new Response<>(State.NOT_FOUND, message, RemoteOptional.empty());
    }

    public static <E> Response<E> unauthorized(String message) {
        Objects.requireNonNull(message);
        return new Response<>(State.UNAUTHORIZED, message, RemoteOptional.empty());
    }

    public static <E> Response<E> forbidden(String message) {
        Objects.requireNonNull(message);
        return new Response<>(State.FORBIDDEN, message, RemoteOptional.empty());
    }

    public static <E> Response<E> badRequest(String message) {
        Objects.requireNonNull(message);
        return new Response<>(State.BAD_REQUEST, message, RemoteOptional.empty());
    }

    public enum State {
        OK,
        NOT_FOUND,
        UNAUTHORIZED,
        FORBIDDEN,
        BAD_REQUEST,
    }

}