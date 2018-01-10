package ca.ocbl.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.ocbl.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// @Query("select u from User u where u.email = ?1")
	public User findByEmail(String email);
}
