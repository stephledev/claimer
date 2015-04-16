package ch.claimer.appserver.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ch.claimer.appserver.methods.CategoryMethod;
import ch.claimer.shared.models.Category;


public class RMITestClient {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {

		// URL definieren
		String url = "rmi://192.168.192.25:9090/Category";
		// Referenz auf das entfernte Objekt holen
		CategoryMethod category = (CategoryMethod) Naming.lookup(url);
		// Methode 'add' des entfernten Objekts aufrufen
		System.out.println(category.getAll());
	}

}
