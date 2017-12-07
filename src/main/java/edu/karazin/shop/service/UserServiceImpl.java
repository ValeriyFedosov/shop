package edu.karazin.shop.service;

import edu.karazin.shop.repository.UserRepository;
import edu.karazin.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUser(String login) {
        return userRepository.findByLogin(login);
    }

}
