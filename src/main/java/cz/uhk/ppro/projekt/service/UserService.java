package cz.uhk.ppro.projekt.service;

import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);

        return newUser;
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(int id) {
        return userRepository.getById(id);
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
