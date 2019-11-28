package net.chrisgrollier.cloud.apps.sample.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

/**
 * @author A651204
 *
 */
@Entity
@Table(name = "USER")
public class UserEntity {

	/** The user identifier */
	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	private Integer id;

	/** The user firstName */
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;

	/** The user lastName */
	@Column(name = "LASTNAME", nullable = false)
	private String lastName;

	/** The user role, possible values {ADMIN, USER} */
	@Column(name = "ROLE", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	/** The user Email */
	@Column(name = "EMAIL", nullable = false)
	private String email;

	/** The user address */
	@Column(name = "ADDRESS")
	private String address;

	
	/** Username*/
	@Column(name = "USERNAME", nullable = false)
	private String username;

	/** Password */
	@Column(name = "PASSWORD", length = 60, nullable = false)
	private String password;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// @formatter:off
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("firstname", firstName)
                          .add("lastname", lastName)
                          .add("role", role)
                          .add("email", email)
                          .add("address", address)
                          .add("username", username)
                          .add("password", password)
                          .toString();
        // @formatter:on
	}

}
