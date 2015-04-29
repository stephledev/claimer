package ch.claimer.shared.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Login implements Serializable, Model {
	
	private static final long serialVersionUID = -2199992593798784171L;
	
	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true)
	private String username;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	
	public Login() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
