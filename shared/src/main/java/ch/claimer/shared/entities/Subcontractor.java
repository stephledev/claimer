package ch.claimer.shared.entities;

import javax.persistence.Entity;

@Entity
public class Subcontractor extends Company {
	
	public Subcontractor() {
		setType(CompanyType.Subcontractor);
	}

}
