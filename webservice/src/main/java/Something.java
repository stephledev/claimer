import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;

import ch.claimer.webservice.util.HibernateUtil;


public class Something {

	public static void main(String[] args) {
		SessionFactory session = HibernateUtil.getSessionFactory();
		Map<String, ClassMetadata> map = session.getAllClassMetadata();
		
		for(ClassMetadata metadata : map.values()){
			String e = metadata.getEntityName();
			System.out.println(e.substring(e.lastIndexOf(".")+1));
			System.out.println(metadata.getMappedClass());
		}
		

	}

}
