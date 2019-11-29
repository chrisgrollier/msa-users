package net.chrisgrollier.cloud.apps.sample.user.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.chrisgrollier.cloud.apps.common.exception.service.EntityNotFoundUnrecoverableException;
import net.chrisgrollier.cloud.apps.common.util.mapping.BidiMapper;
import net.chrisgrollier.cloud.apps.sample.user.dao.UserDAO;
import net.chrisgrollier.cloud.apps.sample.user.entity.UserEntity;
import net.chrisgrollier.cloud.apps.sample.user.model.User;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final BidiMapper<User, UserEntity> mapper;

    /**
     * Build a new instance injecting given arguments.
     * 
     * @param userDAO           user DAO bean
     * @param mapper            mapper bean
     * @param logServiceFactory {@link LogServiceFactory} bean
     */
    @Autowired
    public UserService(final UserDAO userDAO, final BidiMapper<User, UserEntity> mapper) {
        this.userDAO = userDAO;
        this.mapper = mapper;
    }

    /**
     * Find all existing users.
     * 
     * @return a collection of {@code User}
     */
    public Collection<User> findAllUsers() {
        return mapper.froms(userDAO.findAll());
    }

    /**
     * Find user by id.
     * 
     * @param id the user identifier as {@code Integer}
     * @return a retrieved {@code User}
     */
    public User findUser(final Integer id) {
        return userDAO.findById(id).map(mapper::from)
                .orElseThrow(() -> new EntityNotFoundUnrecoverableException("Could not find user with id = {0}", "user.not.found", id));
    }

    /**
     * Find user by username.
     * 
     * @param username the user username as {@code String}
     * @return a retrieved {@code User}
     */
    public User findUser(final String username) {
        return userDAO.findByUsername(username).map(mapper::from).orElseThrow(
                () -> new EntityNotFoundUnrecoverableException("Could not find user with username = {0}", "user.not.found.with.username", username));
    }

    /**
     * Check if user with the given id exist
     * 
     * @param id the id as {@code Integer}
     * @return <code>true</code> if the user exist <code>false</code> otherwise.
     */
    public boolean existsUserById(final Integer id) {
        return userDAO.existsById(id);
    }

    /**
     * Check if user with the given username exist
     * 
     * @param username the username as {@code String}
     * @return <code>true</code> if the user exist <code>false</code> otherwise.
     */
    public boolean existsUserByUsername(final String username) {
        return userDAO.existsUserByUsername(username);
    }

    /**
     * Saves a given user.
     * 
     * @param user the {@code User} to be added in database
     * @return the saved user
     */
    @Transactional
    public User addUser(final User user) {
        return mapper.from(userDAO.save(mapper.to(user)));
    }

    /**
     * Updates the user with the given id .
     * 
     * @param id   user identifier
     * @param user the {@code User} to be updated.
     * @return the saved user
     */
    @Transactional
    public User updateUser(final Integer id, final User user) {
        UserEntity userEntity = userDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundUnrecoverableException("Unable to update user with id = {0} cause could not find it",
                        "update.user.not.found", id));
        mapper.copyTo(user, userDAO.save(userEntity));
        return mapper.from(userEntity);
    }

    /**
     * Delete user by id.
     * 
     * @param id the user identifier as {@code Integer}
     */
    @Transactional
    public void deleteUser(final Integer id) {
        try {
            userDAO.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundUnrecoverableException("Unable to delete user with id = {0} cause could not find it", e, "delete.user.not.found",
                    id);
        }

    }

    /**
     * Authenticate user .
     * 
     * @param USER the user as {@code USER}
     */
    @Transactional
    public User authenticate(final User user) {
        String username = user.getUsername();
        final User userInfo = userDAO.findByUsername(username).map(mapper::from).orElseThrow(() -> new EntityNotFoundUnrecoverableException(
                "Unable to authentificate user with username = {0} cause could not find it", "authenticate.user.not.found.with.username", username));
        if (userInfo.getPassword().equals(user.getPassword())) {
            return userInfo;
        }
        throw new EntityNotFoundUnrecoverableException("Unable to authentificate user with id = {0}", "authenticate.user.bad.credentials", username);

    }
}
