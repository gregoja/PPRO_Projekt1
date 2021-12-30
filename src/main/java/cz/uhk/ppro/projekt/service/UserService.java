package cz.uhk.ppro.projekt.service;

import cz.uhk.ppro.projekt.entity.Order;
import cz.uhk.ppro.projekt.entity.OrderRow;
import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.model.OrderSummary;
import cz.uhk.ppro.projekt.repository.OrderRepository;
import cz.uhk.ppro.projekt.repository.OrderRowRepository;
import cz.uhk.ppro.projekt.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String username, String password, Timestamp timestamp) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setAdministrator((byte) 0);
        newUser.setUserId(2);
        newUser.setRegistrationTimestamp(timestamp);
        userRepository.save(newUser);

        return newUser;
    }

    public boolean usernameExists(String username) {
        List<User> users;
        users = userRepository.findAll();
        for (User user: users) {
            if(user.getUsername().toLowerCase(Locale.ROOT).equals(username.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }

        return false;

    }
}
