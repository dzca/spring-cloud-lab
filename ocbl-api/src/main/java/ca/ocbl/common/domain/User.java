package ca.ocbl.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ca.ocbl.common.enums.UserStatuEnum;

@Entity
public class User extends BaseEntity {
	
	private static final long serialVersionUID = 6960017365343378319L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String userName;

	@Column(name = "status")
	private UserStatuEnum status;
	
	// @Size(max = 15)
	// @Column(name = "phone_number", unique = true)
	@Column(unique=true)
	private String email;

	public User() {
		status = status.UNREGISTERED;
	}

	public User(String userName, String email, UserStatuEnum status) {
		super();
		this.userName = userName;
		this.email = email;
		this.status = status;
	}

	public UserStatuEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatuEnum status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("User [id=%d, name='%s', email='%s']",
				id, userName, email);
	}
}
