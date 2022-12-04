package com.kamelia.ebc.common.base;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiFunction;

public final class Response<E> implements Serializable {

    private final State state;
    private final String message;
    private final RemoteOptional<E> body;


    public Response(State state, String message, RemoteOptional<E> body) {
        Objects.requireNonNull(state);
        Objects.requireNonNull(message);
        Objects.requireNonNull(body);
        this.state = state;
        this.message = message;
        this.body = body;
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

    public <Ex extends Exception> E orElseThrow(BiFunction<State, String, ? extends Ex> exceptionSupplier) throws Ex {
        if (state == State.OK) {
            return body.get();
        }
        throw exceptionSupplier.apply(state, message);
    }

    public E orElseThrow() {
        return orElseThrow(new BiFunction<State, String, IllegalStateException>() {
            @Override
            public IllegalStateException apply(State state, String message) {
                return new IllegalStateException("State: " + state + ", message: " + message);
            }
        });
    }

    public State state() {
        return state;
    }

    public String message() {
        return message;
    }

    public RemoteOptional<E> body() {
        return body;
    }

    public enum State {
        OK,
        NOT_FOUND,
        UNAUTHORIZED,
        FORBIDDEN,
        BAD_REQUEST,
    }
}
