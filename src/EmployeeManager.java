// Poniższe linie kodu importują potrzebne klasy: File, IOException oraz Scanner.
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeManager {
    private static final String EMPLOYEES_FILE = "employees.txt";

    /**
    * Linia ta deklaruje klasę EmployeeManager
    * oraz stałą EMPLOYEES_FILE, która przechowuje nazwę pliku z danymi pracowników.
    **/
    public static boolean validateEmployee(String login, String password) {

        /*Metoda validateEmployee jest statyczna,
         * przyjmuje dwa argumenty typu String: login oraz password.
         * Metoda ta zwraca wartość typu boolean.
         * W tej linii kodu tworzony jest obiekt Scanner, który będzie używany do odczytu pliku EMPLOYEES_FILE.*/

        try (Scanner scanner = new Scanner(new File(EMPLOYEES_FILE))) {
            while (scanner.hasNextLine()) {
                /*
                  W tym bloku kodu następuje odczytanie pliku linia po linii.
                  Każda linia pliku jest dzielona na części za pomocą metody split, które są przechowywane w tablicy parts.
                  Następnie pobierane są login i hasło pracownika z odpowiednich części wiersza.
                  Jeśli login i hasło podane w argumentach są takie same jak login i hasło wiersza z pliku, to metoda zwraca true.
                  Jeśli nie, przeszukiwanie pliku kontynuuje się aż do końca pliku.

                 */
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String employeeLogin = parts[0];
                String employeePassword = parts[1];
                if (login.equals(employeeLogin) && password.equals(employeePassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            /*
            W tej części kodu obsługiwany jest wyjątek, który może wystąpić podczas odczytu pliku.
            Jeśli wyjątek zostanie rzucony, w konsoli zostanie wyświetlony komunikat o błędzie.
            W przypadku niepowodzenia odczytu pliku zwracana jest wartość false.
            Użycie try-with-resources w tej części kodu zapewnia, że plik zostanie automatycznie zamknięty po zakończeniu odczytu, nawet w przypadku wystąpienia wyjątku.
            */
            System.out.println("Nie udało się odczytać pliku " + EMPLOYEES_FILE);
        }
        return false;
    }
}