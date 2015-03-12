package ch.claimer.shared.models;

import javax.persistence.Entity;

@Entity
public class GeneralContractor extends Company {
	
	public GeneralContractor() {
		setType(CompanyType.GeneralContractor);
	}

}
