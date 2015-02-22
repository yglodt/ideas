package name.glodt.yves.ideas.dao;

import java.util.UUID;

import name.glodt.yves.ideas.domain.Vote;

import org.springframework.data.repository.CrudRepository;

public interface VoteDao extends CrudRepository<Vote, UUID> {

	// http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation

}
