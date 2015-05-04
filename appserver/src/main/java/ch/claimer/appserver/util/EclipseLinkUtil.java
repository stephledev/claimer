package ch.claimer.appserver.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EclipseLinkUtil {
	
   private static EntityManagerFactory entityManagerFactory = null;
 
   public static synchronized EntityManagerFactory getEntityManagerFactory() {
	   if (entityManagerFactory == null) {
		   entityManagerFactory = Persistence.createEntityManagerFactory("claimer");
	   }
	   
	   return entityManagerFactory ;
   }
}