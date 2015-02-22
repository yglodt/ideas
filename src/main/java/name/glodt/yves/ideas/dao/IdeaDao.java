package name.glodt.yves.ideas.dao;

import java.util.List;
import java.util.UUID;

import name.glodt.yves.ideas.domain.Idea;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IdeaDao extends CrudRepository<Idea, UUID> {

	// http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation

	@Query("from Idea where parent is null order by dateCreated desc")
	public List<Idea> findAllOrderByDateCreatedDesc();

	@Query("select p from Idea p left join p.votes as pc group by p order by count(pc) desc")
	public List<Idea> findAllOrderByNumberOfVotes();

	public List<Idea> findByParentOrderByDateCreatedDesc(Idea idea);

}
