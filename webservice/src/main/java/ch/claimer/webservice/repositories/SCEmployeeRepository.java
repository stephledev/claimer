package ch.claimer.webservice.repositories;

import java.util.List;

import ch.claimer.shared.models.SCEmployee;

public interface SCEmployeeRepository {
	
	List<SCEmployee> getBySubcontractor(Integer id);

}
