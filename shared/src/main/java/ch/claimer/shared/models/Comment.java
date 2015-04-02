package ch.claimer.shared.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * @author Stephan Beeler
 */

@Entity
public class Comment implements Serializable, Model {
	

	private static final long serialVersionUID = -7169553372897926055L;
	
	@Id
	@GeneratedValue
	private int id;
	@Generated(value=GenerationTime.INSERT)
	private Date created;
	private String content;
	@ManyToOne
	private Supervisor supervisor;
	
	public Comment() {
		
	}

	public int getId() {
		return id;
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

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	
}
