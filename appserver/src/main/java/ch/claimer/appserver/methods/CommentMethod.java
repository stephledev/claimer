package ch.claimer.appserver.methods;

import java.util.List;

import ch.claimer.shared.models.Comment;

public interface CommentMethod extends Method<Comment> {
	public List<Comment> getByContact(Integer id);
	public List<Comment> getBySupervisor(Integer id);
}
