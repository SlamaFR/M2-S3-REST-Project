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

	public PurchaseResponse buyBike(String userId, String bikeId) throws RemoteException {
		System.out.println("User " + userId + "- bike " + bikeId);
		System.out.println("a");
		UUID bikeUUID = UUID.fromString(bikeId);
		System.out.println("b");
		RemoteOptional<Bike> optionalBike = BIKE_STORAGE.findById(bikeUUID);
		System.out.println("c  " + optionalBike);

		if (optionalBike.isEmpty()) {
			System.out.println("c end");
			return new PurchaseResponse("This bike does not exist", 404);
		}

		System.out.println("d");
		Bike bike = optionalBike.get();
		System.out.println("e " + bike);
		List<Pair<User, BikeOrder>> history = bike.ordersHistory();
		System.out.println("f " + history);
		if (history.isEmpty()) {
			System.out.println("f end");
			return new PurchaseResponse("This bike is not for sale", 403);
		}

		System.out.println("g");
		Pair<User, BikeOrder> lastOrder = history.get(history.size() - 1);
		System.out.println("h " + lastOrder);
		int price = lastOrder.second().state().value();
		System.out.println("i " + price);

		Response bankResponse = BANK_SERVICE.checkBalance(userId, price);
		System.out.println("j " + bankResponse);
		if (!bankResponse.getState().equals("OK")) {
			System.out.println("j end");
			return new PurchaseResponse("You don't have enough money", 403);
		}

		System.out.println("k");

		BANK_SERVICE.debit(userId, price);
		System.out.println("l");
		BANK_SERVICE.credit(bike.owner().id().toString(), price);
		System.out.println("m");
		BIKE_STORAGE.changeBikeOwner(bikeUUID, UUID.fromString(userId));
		System.out.println("n");
		return new PurchaseResponse("Purchase successful", 200);
	}
}
