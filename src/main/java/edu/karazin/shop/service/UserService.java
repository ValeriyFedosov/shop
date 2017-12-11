package edu.karazin.shop.service;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUser(Long id);

    User getUser(String login);

    User getCurrentAuthenticatedUser();

    List<User> getAllUsers();

    boolean checkForCart(User user);

    List<BasketItem> getCartForUsers(User user);
}
