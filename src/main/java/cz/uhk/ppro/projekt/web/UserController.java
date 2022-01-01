package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.service.PasswordAuthentication;
import cz.uhk.ppro.projekt.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("userRegistration")
    @ResponseBody
    public void formRegisterUser(@RequestBody @Valid Map<String, String> registerData, HttpSession session) {

        String pattern = "[a-zA-Z0-9]{4,}";
        if (!registerData.get("username").matches(pattern)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nesprávný tvar uživatelského jména!");
        } else if (!registerData.get("password").matches(pattern)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nesprávný tvar hesla!");
        }

        User newUser = userService.createUser(registerData.get("username"), passwordAuthentication.hash(registerData.get("password").toCharArray()));
        session.setAttribute("userId", newUser.getUserId());    // TODO: prihlasit uzivatele hned po zaregistrovani?
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Uživatel již existuje!");
    }

    @PostMapping("userLogin")
    @ResponseBody
    public void formLoginUser(@RequestBody Map<String, String> loginData, HttpSession session) {
        User user = userService.findByUsername(loginData.get("username"));

        if (user != null && passwordAuthentication.authenticate(loginData.get("password").toCharArray(), user.getPassword())) {
            session.setAttribute("userId", user.getUserId());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Kombinace přihlašovacího jména a hesla je neplatná!"); // TODO: spravne cislo chyby?
        }
    }
}