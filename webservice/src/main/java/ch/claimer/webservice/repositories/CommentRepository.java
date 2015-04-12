package ch.claimer.webservice.repositories;

import java.util.List;

import ch.claimer.shared.models.Comment;

public interface CommentRepository {
	
	List<Comment> getBySupervisor(Integer id);
	List<Comment> getByContact(Integer id);
}
