package com.company;

import Account.Account;
import Account.FileAccountManager;
import Account.FileService;
import Exceptions.AccountBlockedException;
import Exceptions.WrongCredentialsException;
import Account.FailedLoginCounter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FileAccountManager manager = new FileAccountManager();

        for (Account account : manager.getAccounts()) {
            account.printInfo();
        }
        System.out.println();

//        // Регистрация нового аккаунта
//        System.out.println("----------------------- Registration -----------------------");
//        Account newAccount = new Account("Romanov Roman Romanovich", "04.04.2004", "romanov@mail.ru", "123456789");
//        try {
//            manager.register(newAccount);
//        } catch (AccountAlreadyExistsException e) {
//            System.out.println(e.getMessage());
//        }
//        for (Account account : manager.getAccounts()) {
//            account.printInfo();
//        }
//        System.out.println();
//
        // Вход в аккаунт

        System.out.print("-------------------------- SignIn --------------------------");
        Account enteredAccount = null;
        String answer = "+";
        while (answer.equals("+")) {
            System.out.println("\nLogin (email): ");
            String email = in.nextLine();
            System.out.println("Password: ");
            String password = in.nextLine();
            try {
                enteredAccount = manager.login(email, password);
            } catch (AccountBlockedException exception) {
                System.out.println(exception.getMessage());
            } catch (WrongCredentialsException exception) {
                System.out.println(exception.getMessage());
                FailedLoginCounter.getInstance().counterAdd(email);
                if (FailedLoginCounter.getInstance().getCounter(email) == 5) {
                    try {
                        manager.findAccount(email).block();
                        System.out.println("Now account '" + email + "' is blocked.\n");
                    } catch (WrongCredentialsException e) {
                        System.out.println(e.getMessage());
                    }
                    FailedLoginCounter.getInstance().updateCounter(email);
                    break;
                }
            } finally {
                if (enteredAccount != null) {
                    break;
                }
                System.out.println("------------------------------------------------------------");
                System.out.println("Continue? (+ or -): ");
                answer = in.nextLine();
            }
        }

        // Удаление аккаунта
        System.out.println("\n------------------------- Deleting -------------------------");
        System.out.println("Login (email): ");
        String email = in.nextLine();
        System.out.println("Password: ");
        String password = in.nextLine();
        try {
            manager.removeAccount(email, password);
            System.out.println("Account '" + email + "' is deleted.\n");
            for (Account account : manager.getAccounts()) {
                account.printInfo();
            }
            System.out.println();
        } catch (WrongCredentialsException e) {
            System.out.println(e.getMessage());
        }

        FileService.getInstance().writeFile(manager.getAccounts());
    }
}
