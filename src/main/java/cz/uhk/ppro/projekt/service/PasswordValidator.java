package cz.uhk.ppro.projekt.service;

import cz.uhk.ppro.projekt.web.errors.InvalidPasswordException;

public class PasswordValidator {

    private static String pattern = "[a-zA-Z0-9]{4,}";

    public static void isPasswordValid(String password) {
        if (!password.matches(pattern)) {
            throw new InvalidPasswordException();
        }
    }
}
