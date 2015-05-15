package ch.claimer.appserver.util;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.pmw.tinylog.Logger;

import com.typesafe.config.ConfigFactory;

import ch.claimer.appserver.controller.Controller;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Image;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.LogEntry;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Principal;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.Role;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Subcontractor;
import ch.claimer.shared.models.Supervisor;
import ch.claimer.shared.models.Type;

/**
 * Hilft sämtliche RMI-Dienste zu registrieren.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 * 
 */
public class RMIUtil {

	/**
	 * Registriert sämtliche RMI-Dienste
	 */
	public static void register() {
		try {

			Map<String, Controller<?>> methods = new HashMap<String, Controller<?>>();

			methods.put("category", new Controller<Category>(Category.class));
			methods.put("comment", new Controller<Comment>(Comment.class));
			methods.put("contact", new Controller<Contact>(Contact.class));
			methods.put("gcemployee", new Controller<GCEmployee>(
					GCEmployee.class));
			methods.put("image", new Controller<Image>(Image.class));
			methods.put("issue", new Controller<Issue>(Issue.class));
			methods.put("logentry", new Controller<LogEntry>(LogEntry.class));
			methods.put("login", new Controller<Login>(Login.class));
			methods.put("principal", new Controller<Principal>(Principal.class));
			methods.put("project", new Controller<Project>(Project.class));
			methods.put("role", new Controller<Role>(Role.class));
			methods.put("scemployee", new Controller<SCEmployee>(
					SCEmployee.class));
			methods.put("state", new Controller<State>(State.class));
			methods.put("subcontractor", new Controller<Subcontractor>(
					Subcontractor.class));
			methods.put("supervisor", new Controller<Supervisor>(
					Supervisor.class));
			methods.put("type", new Controller<Type>(Type.class));

			Registry registry = LocateRegistry.createRegistry(ConfigFactory
					.load().getInt("rmi.port"));
			
			Logger.info("RMI-Dienst läuft auf Port: " + ConfigFactory
					.load().getInt("rmi.port"));

			if (registry != null) {
				for (Entry<String, Controller<?>> method : methods.entrySet()) {
					registry.rebind(method.getKey(), method.getValue());
					Logger.info(method.getKey() + " wurde registriert.");
				}
			}

		} catch (RemoteException e) {
			Logger.error(e, "Initialiserung des RMI-Dienstes fehlgeschlagen");
		}
	}
}
