package edu.karazin.shop.service.impl;

import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.repository.UserRepository;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        try {
            for (User o : userRepository.findAllBy(user.getRole())) {
                if (o.equals(user)) {
                    return null;
                }
            }
        } catch (NoSuchElementException e) {
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
        return userRepository.findAllBy(Role.ROLE_USER);
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
