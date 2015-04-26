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

import org.eclipse.persistence.indirection.IndirectList;

public class RMITestClient {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {

		// URL definieren
		String url = "rmi://127.0.0.1:9090/Login";
		// Referenz auf das entfernte Objekt holen
		Method<Login> category = (Method<Login>) Naming.lookup(url);
		// Methode 'add' des entfernten Objekts aufrufen
		System.out.println(category.getAll());
	}

}