package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.service.PasswordAuthentication;
import cz.uhk.ppro.projekt.service.PasswordValidator;
import cz.uhk.ppro.projekt.service.UserService;
import cz.uhk.ppro.projekt.web.UserController;
import cz.uhk.ppro.projekt.web.errors.InvalidPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Map;

@SpringBootTest
@Transactional
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Autowired
    private PasswordAuthentication passwordAuthentication;

    @Autowired
    private HttpSession session;

    @Test
    void userOk() {
        User user = userService.createUser("abc1",
                passwordAuthentication.hash("abc2".toCharArray()));
    }

    @Test
    void registerUser() {
        String username = "username";
        String password = "password1234";
        Map<String, String> registerData = Map.of("username", username, "password", password);
        userController.formRegisterUser(registerData);
    }

    @Test
    void loginUser() {
        String username = "username";
        String password = "password1234";
        User user = userService.createUser(username, passwordAuthentication.hash(password.toCharArray()));
        Map<String, String> registerData = Map.of("username", username, "password", password);
        userController.formLoginUser(registerData, session);
    }

    @Test
    void wrongCombinationofLoginAndPassword() {
        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            Map<String, String> registerData = Map.of("username", "username", "password", "password");
            userController.formLoginUser(registerData, session);
        });
    }

    @Test
    void shortUsernameException() {
        ConstraintViolationException thrown = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            User user = userService.createUser("a3c",
                    passwordAuthentication.hash("abcd".toCharArray()));
        });
    }

    @Test
    void notValidHashedPassword() {
        ConstraintViolationException thrown = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            User user = userService.createUser("a2cd","password1234");
        });
    }

    @Test
    void notValidPasswordException() {
        InvalidPasswordException thrown = Assertions.assertThrows(InvalidPasswordException.class, () -> {
            PasswordValidator.isPasswordValid("p2s");
        });

    }
}
