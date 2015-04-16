package ch.claimer.appserver.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ch.claimer.appserver.controller.CategoryController;
import ch.claimer.appserver.methods.CategoryMethod;

public class RMIService {

	public static void main(String[] args) {
		try {

			CategoryMethod category = new CategoryController();

			Registry reg = LocateRegistry.createRegistry(9090);

			if (reg != null) {
			reg.rebind("Category", category);
			}

		} catch (RemoteException re) {
			re.printStackTrace();
		}

	}

}
