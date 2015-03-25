package ch.claimer.shared.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Login implements Serializable {
	
	private static final long serialVersionUID = -2199992593798784171L;
	
	@Id
	@GeneratedValue
	private int id;

	public Login() {
		
	}
	
	

}
