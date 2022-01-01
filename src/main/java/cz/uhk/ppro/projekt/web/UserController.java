package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.service.PasswordAuthentication;
import cz.uhk.ppro.projekt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    public User formRegisterUser(@RequestBody @Valid User user, HttpSession session) {
        User user1 = userService.findByUsername(user.getUsername());
        if (user1 != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username already exist!");    // TODO: spravne cislo chyby?
        }

        String pattern = "[a-zA-Z0-9]{4,}";
        if (!user.getUsername().matches(pattern)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nesprávný tvar uživatelského jména!");
        } else if (!user.getPassword().matches(pattern)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nesprávný tvar hesla!");
        }

        user.setPassword(passwordAuthentication.hash(user.getPassword().toCharArray()));
        User newUser = userService.createUser(user);
        session.setAttribute("userId", newUser.getUserId());    // TODO: prihlasit uzivatele hned po zaregistrovani?

        return newUser;
    }

    @PostMapping("formLoginUser")
    @ResponseBody
    public User formLoginUser(@RequestBody Map<String, String> loginData, HttpSession session) {
        User user = userService.findByUsername(loginData.get("username"));
        if (user != null && passwordAuthentication.authenticate(loginData.get("password").toCharArray(), user.getPassword())) {
            session.setAttribute("userId", user.getUserId());
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username did not exist!"); // TODO: spravne cislo chyby?
        }
    }
}