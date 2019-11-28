/*
 * Creation : 03 Sep 2019
 */
package net.chrisgrollier.cloud.apps.sample.user.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.chrisgrollier.cloud.apps.sample.user.entity.UserEntity;


/**
 * A basic User DAO based spring data
 */
@Repository
public interface UserDAO extends CrudRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findByUsername(String username);
	
	
	boolean existsUserByUsername(String username);
}
