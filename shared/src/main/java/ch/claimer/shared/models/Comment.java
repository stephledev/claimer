package ch.claimer.shared.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Stephan Beeler
 */

@Entity
public class Comment implements Serializable, Model {
	

	private static final long serialVersionUID = -7169553372897926055L;
	
	@Id
	@GeneratedValue
	private int id;
	@Temporal(TemporalType.DATE)
	private Date created;
	private String content;
	@ManyToOne
	private Person person;
	
	public Comment() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
