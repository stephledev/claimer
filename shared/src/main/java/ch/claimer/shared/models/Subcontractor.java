package ch.claimer.shared.models;

import javax.persistence.Entity;

@Entity
public class Subcontractor extends Company {
	
	public Subcontractor() {
		setType(CompanyType.Subcontractor);
	}

}
