package net.chrisgrollier.cloud.apps.sample.user.mapping;

import org.springframework.stereotype.Component;

import net.chrisgrollier.cloud.apps.common.util.mapping.AbstractBidiMapper;
import net.chrisgrollier.cloud.apps.sample.user.entity.UserEntity;
import net.chrisgrollier.cloud.apps.sample.user.model.User;

@Component
public class UserModelEntityMapper extends AbstractBidiMapper<User, UserEntity> {

	public UserModelEntityMapper() {
		super(User.class, UserEntity.class);
	}

	@Override
	public User copyFrom(User user, UserEntity userEntity) {
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setAddress(userEntity.getAddress());
        user.setRole(userEntity.getRole());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        return user;
	}

	@Override
	public UserEntity copyTo(User user, UserEntity userEntity) {
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setAddress(user.getAddress());
        userEntity.setRole(user.getRole());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        return userEntity;
	}
}
