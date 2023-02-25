import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeManager {
    private static final String EMPLOYEES_FILE = "employees.txt";

    public static boolean validateEmployee(String login, String password) {
        try (Scanner scanner = new Scanner(new File(EMPLOYEES_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String employeeLogin = parts[0];
                String employeePassword = parts[1];
                if (login.equals(employeeLogin) && password.equals(employeePassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Nie udało się odczytać pliku " + EMPLOYEES_FILE);
        }
        return false;
    }
}