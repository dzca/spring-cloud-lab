package ca.ocbl.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.ocbl.common.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// @Query("select u from User u where u.email = ?1")
	public User findByEmail(String email);
}
