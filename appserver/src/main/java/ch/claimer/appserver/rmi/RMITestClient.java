package ch.claimer.appserver.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ch.claimer.appserver.methods.Method;
import ch.claimer.shared.models.Category;


public class RMITestClient {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {

		// URL definieren
		String url = "rmi://192.168.192.25:9090/Category";
		// Referenz auf das entfernte Objekt holen
		Method<Category> category = (Method<Category>) Naming.lookup(url);
		// Methode 'add' des entfernten Objekts aufrufen
		List<String> values = new ArrayList<String>();
		values.add("Test");
		values.add("Test2");
		System.out.println(category.getByProperty("name", values).get(0).getName());
	}

}
