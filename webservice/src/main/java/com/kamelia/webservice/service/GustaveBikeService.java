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
import com.kamelia.webservice.dto.PurchaseResponse;

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

	public PurchaseResponse buyBike(String userId, String bikeId, String currency) throws RemoteException {
		UUID bikeUUID = UUID.fromString(bikeId);
		RemoteOptional<Bike> optionalBike = BIKE_STORAGE.findById(bikeUUID);

		if (optionalBike.isEmpty()) {
			return new PurchaseResponse("This bike does not exist", 404);
		}

		Bike bike = optionalBike.get();
		List<Pair<User, BikeOrder>> history = bike.ordersHistory();
		if (history.isEmpty()) {
			return new PurchaseResponse("This bike is not for sale", 403);
		}

		Pair<User, BikeOrder> lastOrder = history.get(history.size() - 1);
		int price = lastOrder.second().state().value();

		Response bankResponse = BANK_SERVICE.checkBalance(userId, price);
		if (!bankResponse.getState().equals("OK")) {
			return new PurchaseResponse("You don't have enough money", 403);
		}

		BANK_SERVICE.debit(userId, currency, price);
		BANK_SERVICE.credit(bike.owner().id().toString(), currency, price);
		BIKE_STORAGE.changeBikeOwner(bikeUUID, UUID.fromString(userId));

		return new PurchaseResponse("Purchase successful", 200);
	}
}
