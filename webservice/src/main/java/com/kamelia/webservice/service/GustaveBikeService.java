package com.kamelia.webservice.service;

import javax.xml.rpc.ServiceException;

import com.kamelia.ebc.bank.BankService;
import com.kamelia.ebc.bank.BankServiceServiceLocator;
import com.kamelia.ebc.bank.dto.Response;
import com.kamelia.ebc.common.base.Bike;
import com.kamelia.ebc.common.base.BikeOrder;
import com.kamelia.ebc.common.base.BikeStorage;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.util.Pair;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

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
			System.out.println("eouep");
			BIKE_STORAGE = (BikeStorage) Naming.lookup("rmi://localhost:1100/BikeStorage");
			System.out.println("baouep");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String buyBike(String userId, String bikeId) throws RemoteException {
		var bikeUUID = UUID.fromString(bikeId);
		var optionalBike = BIKE_STORAGE.findById(bikeUUID);

		if (optionalBike.isEmpty()) {
			return "This bike does not exist";
		}

		var bike = optionalBike.get();
		var history = bike.ordersHistory();
		if (history.isEmpty()) {
			return "This bike is not available";
		}

		var lastOrder = history.get(history.size() - 1);
		var price = lastOrder.second().state().value();

		var bankResponse = BANK_SERVICE.checkBalance(userId, price);
		if (!bankResponse.getState().equals("OK")) {
			return "You don't have enough money";
		}

		BANK_SERVICE.debit(userId, price);


		Set<Bike> oe = BIKE_STORAGE.allBikes().orElseThrow();
		return oe.stream().map(new Function<Bike, String>() {
			@Override
			public String apply(Bike b) {
				try {
					return b.id().toString();
				} catch (RemoteException e) {
					throw new RuntimeException(e);
				}
			}
		}).collect(Collectors.joining(", "));
	}
	
	public String f() throws RemoteException {
		var res = BANK_SERVICE.checkBalance("74b3e6cb-c6cc-4a62-895b-b9ee8f66083e", 10);
		
		return res.getState() + " | balance: " + res.getBalance();
	}

}
