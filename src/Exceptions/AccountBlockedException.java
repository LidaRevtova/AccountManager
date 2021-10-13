package Exceptions;

public class AccountBlockedException extends Exception {
    public AccountBlockedException() {
        super("EXCEPTION: This account is blocked");
    }
}
