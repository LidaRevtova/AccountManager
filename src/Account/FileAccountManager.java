package Account;

import Exceptions.AccountAlreadyExistsException;
import Exceptions.AccountBlockedException;
import Exceptions.WrongCredentialsException;

import java.util.List;

public class FileAccountManager implements AccountManager {
    private List<Account> accounts;

    public FileAccountManager() {
        accounts = FileService.getInstance().readFile();
    }

    @Override
    public void register(Account newAccount) throws AccountAlreadyExistsException {
        for (Account account : accounts) {
            if (account.getEmail().equals(newAccount.getEmail())) {
                throw new AccountAlreadyExistsException();
            }
        }
        accounts.add(newAccount);
    }

    @Override
    public Account login(String email, String password) throws WrongCredentialsException, AccountBlockedException {
        for (Account account : accounts) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                if (account.isBlocked()) {
                    throw new AccountBlockedException();
                }
                return account;
            }
        }
        throw new WrongCredentialsException();
    }

    @Override
    public void removeAccount(String email, String password) throws WrongCredentialsException {
        for (Account account : accounts) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                accounts.remove(account);
                return;
            }
        }
        throw new WrongCredentialsException();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account findAccount(String email) throws WrongCredentialsException {
        for (Account account:accounts){
            if(account.getEmail().equals(email)){
                return account;
            }
        }
        throw new WrongCredentialsException();
    }
}
