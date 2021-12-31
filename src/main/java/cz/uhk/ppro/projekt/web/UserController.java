package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.service.PasswordAuthentication;
import cz.uhk.ppro.projekt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordAuthentication passwordAuthentication;

    public UserController(UserService userService, PasswordAuthentication passwordAuthentication) {
        this.userService = userService;
        this.passwordAuthentication = passwordAuthentication;
    }

    @GetMapping("login")
    public String renderLoginPage() {
        return "login";
    }

    @GetMapping("registration")
    public String renderRegistrationPage() {
        return "registration";
    }

    @PostMapping("formRegisterUser")
    @ResponseBody
    public User formRegisterUser(@RequestBody @Valid User user, HttpSession session) throws InvalidKeySpecException, NoSuchAlgorithmException {
        /*if (userService.usernameExists(list.get(0))) {
            //return "products";      // TODO: login souhlasi s jinym, vyhodit chybu přes DB
        }*/

        user.setPassword(passwordAuthentication.hash(user.getPassword().toCharArray()));
        User newUser = userService.createUser(user);
        session.setAttribute("userId", newUser.getUserId());

        return newUser;
    }

    @PostMapping("formLoginUser")
    @ResponseBody
    public void formLoginUser(@RequestBody Map<String, String> loginData) {
        // loginData ("username" -> login, "password" -> heslo)
        // TODO: vyhledat login
        System.out.println(userService.findByUsername("heslo"));
        // TODO: zkontrolovat heslo
        // TODO: udelat nalezity krok
    }
}