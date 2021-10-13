package Account.AccountManager;

import Account.Account;
import Exceptions.AccountAlreadyExistsException;
import Exceptions.AccountBlockedException;
import Exceptions.WrongCredentialsException;

public interface AccountManager {
    void register (Account account) throws AccountAlreadyExistsException;
    Account login(String email, String password) throws WrongCredentialsException, AccountBlockedException;
    void removeAccount(String email, String password) throws WrongCredentialsException;
}
