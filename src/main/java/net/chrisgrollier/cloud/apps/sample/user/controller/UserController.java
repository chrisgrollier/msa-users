package net.chrisgrollier.cloud.apps.sample.user.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.chrisgrollier.cloud.apps.sample.user.model.User;
import net.chrisgrollier.cloud.apps.sample.user.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/v1/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;

	@Value("${user.env}")
	private String env;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@ApiOperation("Find all users.")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public Collection<User> getUsers() {
		return userService.findAllUsers();
	}

	@ApiOperation("Find user by the given identifier.")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE, value = "/{id}")
	public User getUserById(@PathVariable("id") Integer userId) {
		final User user = userService.findUser(userId);
		return user;
	}

	@ApiOperation("Find user by username.")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE, value = "/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		final User user = userService.findUser(username);
		return user;
	}

	@ApiOperation("Get user role by user id.")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE, value = "/role/{id}")
	public String findUserRoleById(@PathVariable("id") Integer id) {
		final User user = userService.findUser(id);
		return user.getRole().toString();
	}

	@ApiOperation("Check if user with given id exists")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE, value = "/exists/id/{id}")
	public Boolean existsByUsername(@PathVariable("id") Integer id) {
		return userService.existsUserById(id);

	}

	@ApiOperation("Check if user with given username exists")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE, value = "/exists/username/{username}")
	public Boolean existsByUsername(@PathVariable("username") String username) {
		return userService.existsUserByUsername(username);

	}

	@ApiOperation("Add a new user.")
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public User addUser(@RequestBody @Valid @NotNull final User user) {
		final User addedUser = userService.addUser(user);
		// Add business log
		LOGGER.info("New user has been added");
		return addedUser;
	}

	@ApiOperation("Update user.")
	@PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_UTF8_VALUE, value = "/{id}")
	public User updateUser(@PathVariable("id") Integer id, @RequestBody @Valid @NotNull final User user) {
		return userService.updateUser(id, user);
	}

	@ApiOperation("Delete user.")
	@DeleteMapping(produces = APPLICATION_JSON_UTF8_VALUE, value = "/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}

	@ApiOperation("User authentification")
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_UTF8_VALUE, value = "/authenticate")
	public User authenticate(@RequestBody @Valid @NotNull final User user) {
		final User autheUser = userService.authenticate(user);
		LOGGER.info("User has been authentified");
		return autheUser;
	}


}
