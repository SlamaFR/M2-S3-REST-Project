package com.kamelia.webservice.service;

import com.kamelia.ebc.bank.BankService;
import com.kamelia.ebc.bank.BankServiceServiceLocator;
import com.kamelia.ebc.bank.dto.Response;
import com.kamelia.ebc.common.base.Bike;
import com.kamelia.ebc.common.base.BikeOrder;
import com.kamelia.ebc.common.base.BikeStorage;
import com.kamelia.ebc.common.base.RemoteOptional;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.util.Pair;

import javax.xml.rpc.ServiceException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public class GustaveBikeService {
	
	public static final BankService BANK_SERVICE;
	public static final BikeStorage BIKE_STORAGE;
	
	static {
		try {
			BANK_SERVICE = new BankServiceServiceLocator().getBankService();
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		try {
			BIKE_STORAGE = (BikeStorage) Naming.lookup("rmi://localhost:1100/BikeStorage");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String buyBike(String userId, String bikeId) throws RemoteException {
		UUID bikeUUID = UUID.fromString(bikeId);
		RemoteOptional<Bike> optionalBike = BIKE_STORAGE.findById(bikeUUID);

		if (optionalBike.isEmpty()) {
			return "This bike does not exist";
		}

		Bike bike = optionalBike.get();
		List<Pair<User, BikeOrder>> history = bike.ordersHistory();
		if (history.isEmpty()) {
			return "This bike is not available";
		}

		Pair<User, BikeOrder> lastOrder = history.get(history.size() - 1);
		int price = lastOrder.second().state().value();

		Response bankResponse = BANK_SERVICE.checkBalance(userId, price);
		if (!bankResponse.getState().equals("OK")) {
			return "You don't have enough money";
		}

		BANK_SERVICE.debit(userId, price);
		BIKE_STORAGE.changeBikeOwner(bikeUUID, UUID.fromString(userId));

		return "Bike bought";
	}
}
