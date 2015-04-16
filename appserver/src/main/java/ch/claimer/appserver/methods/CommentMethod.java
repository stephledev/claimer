package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.Comment;

public interface CommentMethod extends Remote {
	public List<Comment> getByContact(Integer id);
	public List<Comment> getBySupervisor(Integer id);
}
