package ca.ocbl.user.api;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.ocbl.common.domain.User;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {
	public User findByEmail(@Param("email") String email);
}
