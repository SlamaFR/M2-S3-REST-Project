package com.kamelia.ebc.common;

import java.io.Serializable;
import java.util.NoSuchElementException;

public final class RemoteOptional<E> implements Serializable {

    private final E element;

    private RemoteOptional(E element) {
        this.element = element;
    }

    public E get() {
        if (element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }

    public boolean isEmpty() {
        return element == null;
    }

    public boolean isPresent() {
        return element != null;
    }

    public static <E> RemoteOptional<E> ofNullable(E element) {
        return new RemoteOptional<>(element);
    }

    public static <E> RemoteOptional<E> empty() {
        return new RemoteOptional<>(null);
    }

}
