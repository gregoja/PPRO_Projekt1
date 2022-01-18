package cz.uhk.ppro.projekt.web.errors;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException()
    {
        super("Invalid Password");
    }
}
