package ch.claimer.webservice.seeds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;

import com.github.javafaker.Faker;

import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Role;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Subcontractor;
import ch.claimer.shared.models.Supervisor;
import ch.claimer.webservice.repositories.hibernate.HibernateDefaultRepository;
import ch.claimer.webservice.util.HibernateUtil;

public class PersonSeed {
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Faker faker = new Faker();
		Random random = new Random();
		
		
		HibernateDefaultRepository<Contact, Integer> contactRepo = new HibernateDefaultRepository<>(Contact.class);
		HibernateDefaultRepository<Supervisor, Integer> supervisorRepo = new HibernateDefaultRepository<>(Supervisor.class);
		HibernateDefaultRepository<SCEmployee, Integer> scEmployeeRepo = new HibernateDefaultRepository<>(SCEmployee.class);
		HibernateDefaultRepository<GCEmployee, Integer> gcEmployeeRepo = new HibernateDefaultRepository<>(GCEmployee.class);
		HibernateDefaultRepository<Subcontractor, Integer> subcontractorRepo = new HibernateDefaultRepository<>(Subcontractor.class);
		HibernateDefaultRepository<Role, Integer> roleRepo = new HibernateDefaultRepository<>(Role.class);
		
		List<Subcontractor> subcontractorList = subcontractorRepo.getAll();	
		
		Role role1 = new Role();
		role1.setName("scemployee");
		roleRepo.store(role1);
		Role role2 = new Role();
		role2.setName("scemployee-admin");
		roleRepo.store(role2);
		Role role3 = new Role();
		role3.setName("gcemployee");
		roleRepo.store(role3);
		Role role4 = new Role();
		role4.setName("contact");
		roleRepo.store(role4);
		Role role5 = new Role();
		role5.setName("supvervisor");
		roleRepo.store(role5);
		Role role6 = new Role();
		role6.setName("gcemployee-admin");
		roleRepo.store(role6);
		
		for (int i = 0; i < 5; i++) { 
			GCEmployee gcEmployee = new GCEmployee();
			gcEmployee.setFirstname(faker.address().firstName());
			gcEmployee.setLastname(faker.address().lastName());
			gcEmployee.setPhone(faker.phoneNumber().phoneNumber());
			gcEmployee.setEmail(faker.internet().emailAddress());
			Login login = new Login();
			login.setUsername(gcEmployee.getFirstname()+"."+gcEmployee.getLastname());
			List<Role> roles = new ArrayList<Role>();
			roles.add(role3);
			login.setRoles(roles);
			gcEmployee.setLogin(login);
			gcEmployeeRepo.store(gcEmployee);
		}
		
		for (int i = 0; i < 5; i++) { 
			Supervisor supervisor = new Supervisor();
			supervisor.setFirstname(faker.address().firstName());
			supervisor.setLastname(faker.address().lastName());
			supervisor.setPhone(faker.phoneNumber().phoneNumber());
			supervisor.setEmail(faker.internet().emailAddress());
			Login login = new Login();
			login.setUsername(supervisor.getFirstname()+"."+supervisor.getLastname());
			List<Role> roles = new ArrayList<Role>();
			roles.add(role5);
			login.setRoles(roles);
			supervisor.setLogin(login);
			supervisorRepo.store(supervisor);
		}
		
		for (int i = 0; i < 40; i++) { 
			Contact contact = new Contact();
			contact.setFirstname(faker.address().firstName());
			contact.setLastname(faker.address().lastName());
			contact.setPhone(faker.phoneNumber().phoneNumber());
			contact.setEmail(faker.internet().emailAddress());
			contact.setSubcontractor(subcontractorList.get(random.nextInt((subcontractorList.size()-1) + 1)));
			Login login = new Login();
			login.setUsername(contact.getFirstname()+"."+contact.getLastname());
			List<Role> roles = new ArrayList<Role>();
			roles.add(role4);
			login.setRoles(roles);
			contact.setLogin(login);
			contactRepo.store(contact);
		}
		
		for (int i = 0; i < 60; i++) { 
			SCEmployee scEmployee = new SCEmployee();
			scEmployee.setFirstname(faker.address().firstName());
			scEmployee.setLastname(faker.address().lastName());
			scEmployee.setPhone(faker.phoneNumber().phoneNumber());
			scEmployee.setEmail(faker.internet().emailAddress());
			scEmployee.setSubcontractor(subcontractorList.get(random.nextInt((subcontractorList.size()-1) + 1)));
			Login login = new Login();
			login.setUsername(scEmployee.getFirstname()+"."+scEmployee.getLastname());
			List<Role> roles = new ArrayList<Role>();
			roles.add(role1);
			login.setRoles(roles);
			scEmployee.setLogin(login);
			scEmployeeRepo.store(scEmployee);
		}

		session.getTransaction().commit();		
	}
}
