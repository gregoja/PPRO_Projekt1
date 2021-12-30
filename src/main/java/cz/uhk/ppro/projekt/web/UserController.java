package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String renderLoginPage(){
        return  "login";
    }

    @PostMapping("formRegisterUser")
    @ResponseBody
    public void formRegisterUser(@RequestParam(name = "login") String login) {
        System.out.println(login);
        /*if (userService.usernameExists(list.get(0))) {
            //return "products";      // TODO: login souhlasi s jinym, vyhodit chybu
        }

        if (!pass.equals(passAgain)) {
            //return "products";      // TODO: hesla nesouhlasi, vyhodit chybu
        }

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 1024);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        User user = userService.createUser(login, enc.encodeToString(hash), timestamp);
        session.setAttribute("userId", user.getUserId());
        return user;*/
        //return "login";             // TODO: kam to presmerovat, kdyz je vse v poradku?
    }
}