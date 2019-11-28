/*
 * Creation : 03 Sep 2019
 */
package net.chrisgrollier.cloud.apps.sample.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import net.chrisgrollier.cloud.apps.sample.user.dao.UserDAO;
import net.chrisgrollier.cloud.apps.sample.user.entity.Role;
import net.chrisgrollier.cloud.apps.sample.user.entity.UserEntity;


/**
 * Used to initialise user repository. For this sample,  memory database (H2 database) are used
 */
@Component
public class DataInit implements ApplicationRunner {
    private final UserDAO userDAO;

    @Autowired
    public DataInit(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = userDAO.count();

        if (count == 0) {
            final UserEntity user1 = new UserEntity();
            user1.setFirstName("Alain");
            user1.setLastName("Robert");
            user1.setRole(Role.USER);
            user1.setEmail("alain.robert@psa.net");
            user1.setAddress("3 rue des marchands, Paris");
            user1.setUsername("ralain");
            user1.setPassword("ralain");

            final UserEntity user2 = new UserEntity();
            user2.setFirstName("Pierre");
            user2.setLastName("Achaichia");
            user2.setRole(Role.USER);
            user2.setEmail("pierre.achaichia@gmail.com");
           // user2.setAddress("28 avenue des  rue des martyres, Grenoble");
            user2.setUsername("apierre");
            user2.setPassword("apierre");

            final UserEntity user3 = new UserEntity();
            user3.setFirstName("Stephanie");
            user3.setLastName("Laroc");
            user3.setRole(Role.ADMIN);
            user3.setEmail("Stephanie.laroc@psa.net");
            user3.setAddress("44 rue marc sangnier, Talence");
            user3.setUsername("lstephanie");
            user3.setPassword("lstephanie");
            

            userDAO.save(user1);
            userDAO.save(user2);
            userDAO.save(user3);
        }

    }

}
