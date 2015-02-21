package com.example.ideas.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.ideas.domain.Vote;

public interface VoteDao extends CrudRepository<Vote, UUID> {

	// http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation

}
