package ch.claimer.appserver.repositories.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.claimer.appserver.repositories.CommentRepository;
import ch.claimer.appserver.util.HibernateUtil;
import ch.claimer.shared.models.Comment;

public class HibernateCommentRepository implements CommentRepository {
	
	private Session session;
	
	public HibernateCommentRepository() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getBySupervisor(Integer id) {
		Query query = session.createQuery("select c from Comment c inner join c.person p where p.id = "+ id);
		List<Comment> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getByContact(Integer id) {
		Query query = session.createQuery("select c from Comment c inner join c.person p where p.id = "+ id);
		List<Comment> list = query.list();
		return list;
	}

}
