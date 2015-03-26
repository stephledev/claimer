package ch.claimer.webservice.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.claimer.webservice.util.HibernateUtil;

public class HibernateService {
	
	private Session session;
	private Transaction transaction;
	
	public Session getSession() {
		return this.session;
	}

	public Session openSession() {
		if(session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		return session;
	}
	
	public Session openSessionwithTransaction() {
		transaction = openSession().beginTransaction();
		return session;
	}
	
	public void closeSession() {
		session.close();
	}
	
	public void closeSessionwithTransaction() {
		transaction.commit();
		closeSession();
	}	
	
}
