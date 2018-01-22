package ca.ocbl.user.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ca.ocbl.common.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByEmail(@Param("email") String mail);
}
