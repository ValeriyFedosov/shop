package edu.karazin.shop.service.impl;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.PurchaseItem;
import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.repository.BasketItemRepository;
import edu.karazin.shop.repository.UserRepository;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CartStore cartStore;
    private final BasketItemRepository basketItemRepository;


    public UserServiceImpl(@Autowired BasketItemRepository basketItemRepository, @Autowired UserRepository userRepository, @Autowired CartStore cartStore) {
        this.userRepository = userRepository;
        this.cartStore = cartStore;
        this.basketItemRepository = basketItemRepository;
    }

    @Override
    public User createUser(User user) {
        try {
            for (User userFromDb : userRepository.findAll()) {
                if (user.getLogin().equals(userFromDb.getLogin()))
                    return null;
                }
            } catch (NullPointerException e) {
            return userRepository.save(user);
        }
        return userRepository.save(user);
    }

    @Transactional
    public User getCurrentAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login;
        if(principal instanceof UserDetails) {
            login = ((UserDetails)principal).getUsername();
        } else {
            login = principal.toString();
        }
        return getUser(login);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            for (User user : userRepository.findAll()) {
                if (user.getRole().equals(Role.ROLE_USER)) {
                    users.add(user);
                }
            }
        } catch (NoSuchElementException e) {
            return null;
        }
            return users;
    }

    @Override
    public void checkForCart(User user) {
        List<BasketItem> basketItems = cartStore.getProducts();
            for (BasketItem basketItem : basketItemRepository.findAll()) {
                if(basketItem.getUid().getId().equals(user.getId())) {
                    basketItem.setProduct(new Product());
                    basketItem.getProduct().setDescription(basketItem.getDescription());
                    basketItem.getProduct().setTitle(basketItem.getTitle());
                    basketItem.getProduct().setId(basketItem.getProduct_id());
                    cartStore.setTotalAmount(cartStore.getTotalAmount() + basketItem.getCountOfProducts());
                    basketItems.add(basketItem);
                }
            }
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUser(String login) {
        return userRepository.findByLogin(login);
    }

}
