package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String renderLoginPage(){
        return  "login";
    }

    @PostMapping("/formRegisterUser")
    public String registerUser(HttpSession session,
                               @RequestParam(value="login", required = true) String login,
                               @RequestParam(value="pass", required = true) String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 1024);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();

        User user = userService.createUser(login, enc.encodeToString(hash), timestamp);
        session.setAttribute("userId", user.getUserId());
        return "login";
    }
}