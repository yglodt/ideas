package com.example.ideas.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "votes")
public class Vote {

	@Id
	@NotNull
	@Type(type = "pg-uuid")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	private UUID	id;

	private String	userCreated;

	private Date	dateCreated;

	@OneToOne
	private Idea	idea;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(final String userCreated) {
		this.userCreated = userCreated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(final Idea idea) {
		this.idea = idea;
	}

}
