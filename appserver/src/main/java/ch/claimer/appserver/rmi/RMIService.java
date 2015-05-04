package ch.claimer.appserver.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ch.claimer.appserver.controller.Controller;
import ch.claimer.shared.methods.Method;
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

public class RMIService {

	public static void main(String[] args) {
		try {
			
			Method<Category> categoryMethod = new Controller<Category>(Category.class);
			Method<Comment> commentMethod = new Controller<Comment>(Comment.class);
			Method<Contact> contactMethod = new Controller<Contact>(Contact.class);
			Method<GCEmployee> gcEmployeeMethod = new Controller<GCEmployee>(GCEmployee.class);
			Method<Image> imageMethod = new Controller<Image>(Image.class);
			Method<Issue> issueMethod = new Controller<Issue>(Issue.class);
			Method<LogEntry> logEntryMethod = new Controller<LogEntry>(LogEntry.class);
			Method<Login> loginMethod = new Controller<Login>(Login.class);
			Method<Principal> principalMethod = new Controller<Principal>(Principal.class);
			Method<Project> projectMethod = new Controller<Project>(Project.class);
			Method<Role> roleMethod = new Controller<Role>(Role.class);
			Method<SCEmployee> scEmployeeMethod = new Controller<SCEmployee>(SCEmployee.class);
			Method<State> stateMethod = new Controller<State>(State.class);
			Method<Subcontractor> subcontractorMethod = new Controller<Subcontractor>(Subcontractor.class);
			Method<Supervisor> supervisorMethod = new Controller<Supervisor>(Supervisor.class);
			Method<Type> typeMethod = new Controller<Type>(Type.class);			

			Registry reg = LocateRegistry.createRegistry(9090);

			if (reg != null) {
				reg.rebind("Category", categoryMethod);
				reg.rebind("Comment", commentMethod);
				reg.rebind("Contact", contactMethod);
				reg.rebind("GCEmployee", gcEmployeeMethod);
				reg.rebind("Image", imageMethod);
				reg.rebind("Issue", issueMethod);
				reg.rebind("LogEntry", logEntryMethod);
				reg.rebind("Login", loginMethod);
				reg.rebind("Principal", principalMethod);
				reg.rebind("Project", projectMethod);
				reg.rebind("Role", roleMethod);
				reg.rebind("SCEmployee", scEmployeeMethod);
				reg.rebind("State", stateMethod);
				reg.rebind("Subcontractor", subcontractorMethod);
				reg.rebind("Supervisor", supervisorMethod);
				reg.rebind("Type", typeMethod);			
				
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}

	}

}
