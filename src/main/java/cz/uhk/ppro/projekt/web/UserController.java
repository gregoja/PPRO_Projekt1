package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.service.PasswordAuthentication;
import cz.uhk.ppro.projekt.service.PasswordValidator;
import cz.uhk.ppro.projekt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.*;
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

    @PostMapping("logout")
    public ResponseEntity logoutUser(HttpSession session) {
        session.removeAttribute("userId");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("registration")
    public String renderRegistrationPage(HttpSession session) {
        session.removeAttribute("userId");
        return "registration";
    }

    @PostMapping("userRegistration")
    @ResponseBody
    public void formRegisterUser(@RequestBody @Valid Map<String, String> registerData){
        PasswordValidator.isPasswordValid(registerData.get("password"));
        User user = userService.createUser(registerData.get("username"),
                passwordAuthentication.hash(registerData.get("password").toCharArray()));
    }

    @PostMapping("userLogin")
    @ResponseBody
    public void formLoginUser(@RequestBody Map<String, String> loginData, HttpSession session) {
        User user = userService.findByUsername(loginData.get("username"));
        if (user != null && passwordAuthentication.authenticate(loginData.get("password").toCharArray(), user.getPassword())) {
            session.setAttribute("userId", user.getUserId());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Kombinace p??ihla??ovac??ho jm??na a hesla je neplatn??!");
        }
    }
}