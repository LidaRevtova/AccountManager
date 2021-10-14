package Account;

import java.util.Date;

public class Account implements CSVConvertible {
    private String FIO;
    private String birthday;
    private String email;
    private String password;
    private boolean blocked;

    Account() {
    }

    Account(String FIO, String birthday, String email, String password, String blocked) {
        this.FIO = FIO;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.blocked = Boolean.parseBoolean(blocked);
    }

    public Account(String FIO, String birthday, String email, String password) {
        this.FIO = FIO;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.blocked = false;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toCSV() {
        return FIO + ";" + birthday + ";" + email + ";" + password + ";" + blocked + "\n";
    }

    public void printInfo() {
        System.out.println("ФИО: " + FIO + " ДР: "+birthday + " email: " + email + " Пароль: " + password + " Заблокирован: " + blocked);
    }

    public void block() {
        this.blocked = true;
    }
}
