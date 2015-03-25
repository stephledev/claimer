package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import ch.claimer.shared.entities.GeneralContractor;
import ch.claimer.webservice.repositories.GeneralContractorRepository;
import ch.claimer.webservice.service.DataProcessorService;
import ch.claimer.webservice.service.JsonDataProcessorService;

public class GeneralContractorController implements Controller<Integer> {

	@Inject
	private GeneralContractorRepository repo;
	private JsonDataProcessorService<GeneralContractor> processor = new JsonDataProcessorService<GeneralContractor>(GeneralContractor.class);

	@Override
	public Response index() {
		//String gcs = processor.write(repo.getAll());
		return Response.status(200).entity("hello").build();
		
	}

	@Override
	public Response show(Integer id) {	
		//String gc = processor.write(repo.getById(id));
		return Response.status(200).entity("hello").build();
	}

	@Override
	public Response store(String gcString) {
		//GeneralContractor gc = processor.read(gcString);
		//repo.store(gc);
		return Response.status(200).entity("General Contractor has been created: " + gcString).build();
		
	}

	@Override
	public Response update(String gcString) {
		//GeneralContractor gc = processor.read(gcString);
		//repo.update(gc);
		return Response.status(200).entity("General Contractor has been updated: " + gcString).build();
		
	}

	@Override
	public Response destroy(String gcString) {
		//GeneralContractor gc = processor.read(gcString);
		//repo.destroy(gc);
		return Response.status(200).entity("General Contractor has been deleted: " + gcString).build();		
	}
	
}
