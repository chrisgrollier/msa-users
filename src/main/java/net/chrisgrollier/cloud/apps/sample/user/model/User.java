package net.chrisgrollier.cloud.apps.sample.user.model;

import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

import io.swagger.annotations.ApiModelProperty;
import net.chrisgrollier.cloud.apps.sample.user.entity.Role;

public class User {

	@ApiModelProperty(position = 1, required = false, value = "The user identifier")
	private Integer id;

	@ApiModelProperty(position = 2, required = true, value = "The user firstName")
	@NotNull(message = "{user.validation.firstname.mandatory}")
	private String firstName;

	@ApiModelProperty(position = 3, required = true, value = "The user lastName")
	@NotNull(message = "The user lastName is mandatory")
	private String lastName;

	@ApiModelProperty(position = 4, required = true, value = "The user role, possible values {ADMIN, USER}")
	@NotNull(message = "user role type is mandatory")
	private Role role;

	@ApiModelProperty(position = 5, required = true, value = "The user Email")
	@NotNull(message = "The user email is mandatory")
	private String email;

	@ApiModelProperty(position = 6, value = "The user address")
	private String address;

	@ApiModelProperty(position = 7, required = true, value = "username")
	private String username;
	
	@ApiModelProperty(position = 8, required = true, value = "password")
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
                          .add("firstName", firstName)
                          .add("lastName", lastName)
                          .add("role", role)
                          .add("email", email)
                          .add("address", address)
                          .add("username", username)
                          .add("password", password)
                          .toString();
        // @formatter:on
	}

}
