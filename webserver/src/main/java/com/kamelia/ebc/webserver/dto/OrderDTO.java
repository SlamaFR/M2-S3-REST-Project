package com.kamelia.ebc.webserver.dto;

import com.kamelia.ebc.common.base.BikeOrder;

import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.util.Pair;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Objects;

public record OrderDTO(
    Instant date,
    String ordererName,
    String comment,
    String returnState
) {

    public static OrderDTO from(Pair<User, BikeOrder> pair) {
        Objects.requireNonNull(pair);
        var user = pair.first();
        var order = pair.second();
        try {
            return new OrderDTO(
                order.instant(),
                user.username(),
                order.comment(),
                order.state().asString()
            );
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
