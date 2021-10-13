package Exceptions;

public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException() {
        super("EXCEPTION: Account with such email already exists");
    }
}
