import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj login: ");
        String login = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();
        if (!EmployeeManager.validateEmployee(login, password)) {
            //sprawdza, czy login i hasło są prawidłowe za pomocą metody validateEmployee z klasy EmployeeManager,
            // wykonuje instrukcję, jeśli login i hasło są nieprawidłowe
            System.out.println("Niepoprawny login lub hasło. Wyjście z programu.");
            return;
        }
        System.out.println("Zalogowano jako " + login);

        List<Product> products = FileManager.readInventoryFromFile(); //wczytuje listę produktów z pliku tekstowego przy pomocy metody readInventoryFromFile z klasy FileManager i przypisuje ją do zmiennej products
        Inventory inventory = new Inventory(products); //tworzy nowy obiekt Inventory o nazwie inventory, używając listy produktów wczytanej z pliku tekstowego

        while (true) {
            System.out.println("\nCo chcesz zrobić?");
            System.out.println("1 - Sprawdź stan magazynowy");
            System.out.println("2 - Dodaj produkt");
            System.out.println("3 - Usuń produkt");
            System.out.println("0 - Wyjście");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inventory.printInventory();
                    break;
                case 2:
                    System.out.print("Podaj nazwę produktu: ");
                    String name = scanner.nextLine();
                    System.out.print("Podaj ilość produktu: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // konsumujemy znak nowej linii
                    Product product = new Product(name, quantity);
                    inventory.addProduct(product);
                    FileManager.writeInventoryToFile(inventory.products);
                    System.out.println("Dodano produkt " + product.getName());
                    break;
                case 3:
                    System.out.println("Podaj nazwę produktu:");
                    name = scanner.nextLine();
                    System.out.print("Podaj ilość produktu do usunięcia: ");
                    int quantityToRemove = scanner.nextInt();
                    inventory.removeProduct(name, quantityToRemove);
                    break;
                case 0:
                    FileManager.writeInventoryToFile(inventory.products);
                    System.out.println("Wyjście z programu.");
                    return;
                default:
                    System.out.println("Niepoprawny wybór.");
                    break;
            }
        }
    }
}