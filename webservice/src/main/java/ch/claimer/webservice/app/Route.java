package ch.claimer.webservice.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ch.claimer.shared.models.GeneralContractor;
import ch.claimer.webservice.repositories.hibernate.HibernateCompanyRepository;
 
@Path("/")
public class Route {
	
	HibernateCompanyRepository hcr = new HibernateCompanyRepository();
 
	@GET
	@Path("/generalcontractor/{id}")
	@Produces("application/json")
	public GeneralContractor getCompanyById(@PathParam("id") int id) {
		return (GeneralContractor) hcr.getById(id);
	} 

 
 
}