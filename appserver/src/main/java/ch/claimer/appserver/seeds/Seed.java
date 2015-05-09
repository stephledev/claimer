package ch.claimer.appserver.seeds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.claimer.appserver.repositories.Repository;
import ch.claimer.shared.models.Model;

/**
 * Bildet die Vorlage um Beispieldaten für die Datenbank aufzusetzen. Iteriert
 * durch alle Seeds und schreibt sie in die Datenbank.
 * 
 * @author Stephan Beeler
 * @author Raoul Ackermann
 * @version 1.0
 * @since 1.0
 */
public abstract class Seed<T extends Model> {

	protected static Map<String, List<?>> seeds = new HashMap<String, List<?>>();
	protected List<T> seed;
	protected Repository<T> repository;

	public static void main(String[] args) {

		PrincipalSeed principalSeed = new PrincipalSeed();
		principalSeed.setup();
		principalSeed.execute();

		CategorySeed categorySeed = new CategorySeed();
		categorySeed.setup();
		categorySeed.execute();

		StateSeed stateSeed = new StateSeed();
		stateSeed.setup();
		stateSeed.execute();

		TypeSeed typeSeed = new TypeSeed();
		typeSeed.setup();
		typeSeed.execute();

		LogEntrySeed logEntrySeed = new LogEntrySeed();
		logEntrySeed.setup();

		RoleSeed roleSeed = new RoleSeed();
		roleSeed.setup();
		roleSeed.execute();

		LoginSeed loginSeed = new LoginSeed();
		loginSeed.setup();

		SubcontractorSeed subcontractorSeed = new SubcontractorSeed();
		subcontractorSeed.setup();
		subcontractorSeed.execute();

		SupervisorSeed supervisorSeed = new SupervisorSeed();
		supervisorSeed.setup();
		supervisorSeed.execute();

		GCEmployeeSeed gcemployeeSeed = new GCEmployeeSeed();
		gcemployeeSeed.setup();
		gcemployeeSeed.execute();

		SCEmployeeSeed scemployeeSeed = new SCEmployeeSeed();
		scemployeeSeed.setup();
		scemployeeSeed.execute();

		ContactSeed contactSeed = new ContactSeed();
		contactSeed.setup();
		contactSeed.execute();

		ProjectSeed projectSeed = new ProjectSeed();
		projectSeed.setup();
		projectSeed.execute();

		CommentSeed commentSeed = new CommentSeed();
		commentSeed.setup();

		IssueSeed issueSeed = new IssueSeed();
		issueSeed.setup();
		issueSeed.execute();

		System.out.println("Seeding successful");

	}

	/**
	 * Setzt Beispieldaten für die Datenbank auf.
	 */
	public abstract void setup();

	/**
	 * Schreibt alle definierte Beispieldaten in die Datenbank.
	 */
	public void execute() {
		for (T t : seed) {
			repository.create(t);
		}
	}
}