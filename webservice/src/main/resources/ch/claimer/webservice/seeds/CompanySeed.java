package ch.claimer.webservice.seeds;

import org.hibernate.Session;

import com.github.javafaker.Faker;

import ch.claimer.shared.models.Subcontractor;
import ch.claimer.webservice.repositories.hibernate.HibernateDefaultRepository;
import ch.claimer.webservice.util.HibernateUtil;

public class CompanySeed {
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Faker faker = new Faker();
		HibernateDefaultRepository<Subcontractor, Integer> repo = new HibernateDefaultRepository<>(Subcontractor.class);
		
		for (int i = 0; i < 20; i++) { 
			Subcontractor sub = new Subcontractor();
			sub.setName(faker.name().name());
			sub.setStreet(faker.address().streetName());
			sub.setZip(faker.address().zipCode());
			sub.setPlace(faker.address().citySuffix());
			sub.setPhone(faker.phoneNumber().phoneNumber());
			sub.setEmail(faker.internet().emailAddress());
			repo.store(sub);
		}
		
		session.getTransaction().commit();
	}
}
