package ch.claimer.webservice.repositories;

import org.hibernate.Session;
import org.junit.*;

import com.github.javafaker.Faker;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Subcontractor;
import ch.claimer.webservice.repositories.hibernate.HibernateSCEmployeeRepository;
import ch.claimer.webservice.services.HibernateService;

import java.net.URISyntaxException;
import java.util.List;

 
/**
 * @author Stephan Beeler
 */
public class TestSCEmployeeRepository {
	
	//private static Faker faker;
 
    @BeforeClass
    public static void oneTimeSetUp() {
    	//faker = new Faker();
    	Subcontractor sub = new Subcontractor();
    	sub.setName("Firma");
    	sub.setEmail("test@test.ch");
    	
    	Subcontractor sub2 = new Subcontractor();
    	sub2.setName("Firmus");
    	sub2.setEmail("test@test.ch");
    	
    	SCEmployee employee = new SCEmployee();
    	employee.setFirstname("Peter");
    	employee.setLastname("Muster");
    	
    	SCEmployee employee2 = new SCEmployee();
    	employee2.setFirstname("David");
    	employee2.setLastname("Pupsi");
    	
    	SCEmployee employee3 = new SCEmployee();
    	employee3.setFirstname("David");
    	employee3.setLastname("Pupsi");
    	
    	employee.setSubcontractor(sub);
    	employee2.setSubcontractor(sub);
    	employee3.setSubcontractor(sub2);
    	
    	HibernateService hibernate = new HibernateService();
        Session session = hibernate.openSessionwithTransaction();
        session.save(sub);
        session.save(sub2);
        session.save(employee);
        session.save(employee2);
        session.save(employee3);
        hibernate.closeSessionwithTransaction();
    }
 
    
    @Test
    public void testGetBySubcontractor() throws URISyntaxException {
    	HibernateSCEmployeeRepository repo = new HibernateSCEmployeeRepository();
    	List<SCEmployee> list = repo.getBySubcontractor(1);
    	for(SCEmployee employee : list) {
    		System.out.print(employee.getFirstname());
    	}
    }


}