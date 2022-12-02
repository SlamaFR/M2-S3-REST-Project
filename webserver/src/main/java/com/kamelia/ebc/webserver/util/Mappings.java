package com.kamelia.ebc.webserver.util;

import com.kamelia.ebc.common.base.Bike;
import com.kamelia.ebc.common.base.BikeOrder;
import com.kamelia.ebc.common.base.ReturnState;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.util.Pair;
import com.kamelia.ebc.webserver.dto.BikeDTO;
import com.kamelia.ebc.webserver.dto.OrderDTO;

import java.rmi.RemoteException;

public final class Mappings {

    private Mappings() {
        throw new AssertionError();
    }

    public static BikeDTO bikeToDTO(Bike bike) {
        try {
            var history = bike.ordersHistory().stream()
                .map(Pair::second)
                .sorted((a, b) -> b.instant().compareTo(a.instant()))
                .toList();
            var state = history.isEmpty() ? ReturnState.AS_NEW : history.get(0).state();
            return new BikeDTO(
                bike.id(),
                bike.owner().username(),
                bike.ordersHistory().stream().map(Mappings::orderToDTO).toList(),
                bike.orderer().isEmpty(),
                state.value()
            );
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static OrderDTO orderToDTO(Pair<User, BikeOrder> pair) {
        var user = pair.first();
        var order = pair.second();
        try {
            return new OrderDTO(
                order.instant(),
                user.username(),
                order.comment(),
                order.state()
            );
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
