package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import ch.claimer.shared.models.GeneralContractor;
import ch.claimer.webservice.repositories.GeneralContractorRepository;

public class GeneralContractorController implements Controller<Integer> {

	@Inject
	GeneralContractorRepository repo;
	
	public void create() {
		
		GeneralContractor company = new GeneralContractor();
	    company.setName("Beeler GmbH");
	    company.setStreet("Neustadtstrasse 31");
	    company.setZip("6003");
	    company.setPlace("Luzern");
	    
	    repo.create(company);
		
	}

	@Override
	public void index() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Response show(Integer id) {
		JsonFactory factory = objectMapper.getFactory();
		repo.getById(id);
		return Response.status(200).entity("test").build();

		
	}

	@Override
	public void store() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
