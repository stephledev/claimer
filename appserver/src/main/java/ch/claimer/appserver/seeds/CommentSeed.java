package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.Supervisor;

/**
 * 
 * 
 * @author Raoul Ackermann
 */

public class CommentSeed extends Seed<Comment> {
	
	public CommentSeed() {
		this.repository = new EclipseLinkRepository<Comment>(Comment.class);
		this.seed = new ArrayList<Comment>();
	}

	@Override
	public void setup() {
		Comment com1 = new Comment();
		com1.setContent("Wurde bereits erledigt bei der ersten Kontrolle.");
		com1.setPerson((Supervisor)seeds.get("Supervisor").get(0));

		
		seed.add(com1);
	
		Seed.seeds.put("Comment", seed);
	}

}
