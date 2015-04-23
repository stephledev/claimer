package ch.claimer.appserver.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ch.claimer.appserver.controller.Controller;
import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Comment;

public class RMIService {

	public static void main(String[] args) {
		try {

			Method<Category> categoryMethod = new Controller<Category>(Category.class);
			Method<Comment> commentMethod = new Controller<Comment>(Comment.class);
			Method<Contact> commentMethod = new Controller<Contact>(Contact.class);
			Method<GCEmployee> commentMethod = new Controller<GCEmployee>(GCEmployee.class);
			Method<Image> commentMethod = new Controller<Comment>(Comment.class);
			Method<Comment> commentMethod = new Controller<Comment>(Comment.class);
			Method<Comment> commentMethod = new Controller<Comment>(Comment.class);
			Method<Comment> commentMethod = new Controller<Comment>(Comment.class);
			Method<Comment> commentMethod = new Controller<Comment>(Comment.class);

			Registry reg = LocateRegistry.createRegistry(9090);

			if (reg != null) {
			reg.rebind("Category", categoryMethod);
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}

	}

}
