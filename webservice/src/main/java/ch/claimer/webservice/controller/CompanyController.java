package ch.claimer.webservice.controller;

import com.google.inject.Inject;

import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Subcontractor;
import ch.claimer.webservice.repositories.CompanyRepository;

public class CompanyController implements Controller<Integer> {

	@Inject
	CompanyRepository repo;
	
	public void create() {
		
		Company company = new Subcontractor();
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
	public void show(Integer id) {
		// TODO Auto-generated method stub
		
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
