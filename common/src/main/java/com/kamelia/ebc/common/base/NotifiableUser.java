package com.kamelia.ebc.common.base;

import java.io.Serializable;
import java.util.Objects;

public record NotifiableUser(User user, Notifier notifiable) implements Serializable {

    public NotifiableUser {
        Objects.requireNonNull(user);
        Objects.requireNonNull(notifiable);
    }

}
