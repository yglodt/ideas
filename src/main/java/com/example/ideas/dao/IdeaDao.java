package com.example.ideas.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.ideas.domain.Idea;

public interface IdeaDao extends CrudRepository<Idea, UUID> {

	// http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation

	@Query("from Idea where parent is null order by dateCreated desc")
	public List<Idea> findAllOrderByDateCreatedDesc();

	// @Query("select * from Idea where parent.id = :parent")
	// public List<Idea> findAllByParent(@Param("parent") UUID idea);
	public List<Idea> findByParentOrderByDateCreatedDesc(Idea idea);

	// public Idea findByName(String name);

}
