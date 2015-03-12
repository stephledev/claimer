package ch.claimer.webservice.app;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;

import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Subcontractor;


public class Test {

	public static void main(String[] args) {        
		
		Configuration configuration = new Configuration().configure();
		configuration.setNamingStrategy(new ImprovedNamingStrategy());
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		Session sess = factory.openSession();
		

		Transaction tx;
		tx = sess.beginTransaction();
		try {
		    
		    Company company = new Subcontractor();
		    company.setName("Beeler GmbH");
		    company.setStreet("Neustadtstrasse 31");
		    company.setZip("6003");
		    company.setPlace("Luzern");
			sess.save(company);
		    tx.commit();
		}
		catch (Exception e) {
		    if (tx!=null) tx.rollback();
		    throw e;
		}
		finally {
		    sess.close();
		}

	}

}
