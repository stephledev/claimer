package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.util.List;

import ch.claimer.appserver.methods.CommentMethod;
import ch.claimer.appserver.repositories.CommentRepository;
import ch.claimer.appserver.repositories.hibernate.HibernateCommentRepository;
import ch.claimer.shared.models.Comment;


public class CommentController extends Controller<Comment> implements CommentMethod {

	private static final long serialVersionUID = -2905267943004594013L;
	private final CommentRepository commentRepository;
	
	protected CommentController() throws RemoteException {
		super(Comment.class);
		this.commentRepository = new HibernateCommentRepository();
	}
	public List<Comment> getByContact(Integer id) {
		session.beginTransaction();
		List<Comment> comments = commentRepository.getByContact(id);
		session.getTransaction().commit();
		return comments;
	}
	
	public List<Comment> getBySupervisor(Integer id) {
		session.beginTransaction();
		List<Comment> comments = commentRepository.getBySupervisor(id);
		session.getTransaction().commit();
		return comments;
	}
}
