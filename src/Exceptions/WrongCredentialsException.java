package Exceptions;

public class WrongCredentialsException extends Exception {
    public WrongCredentialsException() {
        super("EXCEPTION: Account with such pair email/password doesn't exist");
    }
}
