package edu.karazin.shop.repository;

import edu.karazin.shop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    User save(User user);

    User findOne(Long id);
}
