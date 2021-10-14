package Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    // instanсe экземпляр класса, следит, чтоб создавалось в ед виде
    private static volatile FileService instance;
    private static final String fileName = "accounts.csv";

    private FileService(){}

    public static FileService getInstance() {
        FileService localInstance = instance;
        if (localInstance == null) {
            // для работы с потоками, если какой то из потоков зашел в синронайзд, то остальные ждут, пока он не закончит
            synchronized (FailedLoginCounter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FileService();
                }
            }
        }
        return localInstance;
    }

    public List<Account> readFile() {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                Account newAccount = new Account(values[0], values[1], values[2], values[3], values[4]);
                accounts.add(newAccount);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не был найден. Проверьте, что вы создали его");
        } catch (IOException e) {
            System.out.println("Произошла беда при чтении строки =(");
        }
        return accounts;
    }

    public void writeFile(List<Account> accounts) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Account account : accounts) {
                bufferedWriter.write(account.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
