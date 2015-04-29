package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Comment;

/**
 * 
 * 
 * @author Raoul Ackermann
 */

public class CommentSeed extends Seed<Comment> {
	
	public CommentSeed() {
		this.repository = new EclipseLinkRepository<Comment>(Comment.class);
		this.list = new ArrayList<Comment>();
	}

	@Override
	public void setup() {
		Comment com1 = new Comment();
		com1.setPerson(null);
		com1.setContent("Wurde bereits erledigt bei der ersten Kontrolle.");

		
		list.add(com1);
	
		this.seeds.put("Comment", list);
	}

}
