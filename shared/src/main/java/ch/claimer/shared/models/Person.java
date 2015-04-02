package ch.claimer.shared.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

/**
  * Represents the base person, providing access to the person's lastname
  * firstname, telephone, email and login.
  * 
  * @author Stephan Beeler
  */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable, Model {

	private static final long serialVersionUID = -7793558619197649513L;
	
	@Id
	@GeneratedValue
	private int id;
	private String lastname;
	private String firstname;
	private String telephone;
	private String email;
	@OneToOne(cascade=CascadeType.ALL)
	private Login login;
	
	public Person() {
		
	}

	public int getId() {
		return id;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Login getLogin() {
		return login;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	
}
