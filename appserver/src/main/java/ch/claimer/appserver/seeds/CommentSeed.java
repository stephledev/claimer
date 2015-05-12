package ch.claimer.appserver.seeds;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.Supervisor;

/**
 * In der CommentSeed-Klasse werden einige mögliche Kommentare in die Datenbank
 * geschrieben. Die Klasse erbt von der Klasse Seed und setzt Comment als
 * Typ-Variable.
 * 
 * @author Raoul Ackermann
 * @version 1.0
 * @since 1.0
 */

public class CommentSeed extends Seed<Comment> {

	public CommentSeed() {
		this.repository = new EclipseLinkRepository<Comment>(Comment.class);
		this.seed = new ArrayList<Comment>();
	}

	@Override
	public void setup() {
		Comment com1 = new Comment();
		com1.setCreated(new GregorianCalendar());
		com1.setContent("Wurde bereits erledigt bei der ersten Kontrolle.");
		com1.setSupervisor((Supervisor) seeds.get("Supervisor").get(0));

		Comment com2 = new Comment();
		com2.setCreated(new GregorianCalendar());
		com2.setContent("Die Lieferung der Farbe entsprach nicht unserer Qualitätsnorm.");
		com2.setSupervisor((Supervisor) seeds.get("Supervisor").get(0));

		Comment com3 = new Comment();
		com3.setCreated(new GregorianCalendar());
		com3.setContent("Die Fensterläden weisen Unwetterschäden auf.");
		com3.setSupervisor((Supervisor) seeds.get("Supervisor").get(0));

		seed.add(com1);
		seed.add(com2);
		seed.add(com3);

		Seed.seeds.put("Comment", seed);
	}

}
