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
@Table(name = "ideas")
public class Idea {

	@Id
	@NotNull
	@Type(type = "pg-uuid")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	private UUID	id;

	private String	name;

	@Column(length = 32768)
	private String	description;

	private String	userCreated;

	private Date	dateCreated;

	@OneToOne
	private Idea	parent;

	public Idea() {
	}

	public Idea(final UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
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

	public Idea getParent() {
		return parent;
	}

	public void setParent(final Idea parent) {
		this.parent = parent;
	}

}
