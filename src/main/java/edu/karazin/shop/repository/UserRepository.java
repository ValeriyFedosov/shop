package edu.karazin.shop.repository;

import edu.karazin.shop.model.User;
import edu.karazin.shop.model.enums.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    User save(User user);

    User findOne(Long id);

    List<User> findAllBy(Role role);
}
