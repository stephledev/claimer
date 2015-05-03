package ch.claimer.appserver.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Role;

import org.eclipse.persistence.indirection.IndirectList;

public class RMITestClient {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {

		// URL definieren
		String url = "rmi://127.0.0.1:9090/Role";
		// Referenz auf das entfernte Objekt holen
		Method<Role> category = (Method<Role>) Naming.lookup(url);
		// Methode 'add' des entfernten Objekts aufrufen
		System.out.println(category.getByProperty("name", "admin").get(0).getValue());
	}

}