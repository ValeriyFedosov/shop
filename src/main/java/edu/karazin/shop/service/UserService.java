package edu.karazin.shop.service;

import edu.karazin.shop.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUser(Long id);

    User getUser(String login);

    User getCurrentAuthenticatedUser();

    List<User> getAllUsers();

    void checkForCart(User user);

}
