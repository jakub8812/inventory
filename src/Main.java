import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String INVENTORY_FILE = "inventory.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj login: ");
        String login = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();
        if (!EmployeeManager.validateEmployee(login, password)) {
            System.out.println("Niepoprawny login lub hasło. Wyjście z programu.");
            return;
        }
        System.out.println("Zalogowano jako " + login);

        FileManager fileManager = new FileManager(INVENTORY_FILE);
        List<Product> products = fileManager.readInventoryFromFile();
        Inventory inventory = new Inventory(products);

        while (true) {
            System.out.println("\nCo chcesz zrobić?");
            System.out.println("1 - Sprawdź stan magazynowy");
            System.out.println("2 - Dodaj produkt");
            System.out.println("3 - Usuń produkt");
            System.out.println("0 - Wyjście");

            int choice = scanner.nextInt();
            scanner.nextLine(); // konsumujemy znak nowej linii

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
                    fileManager.writeInventoryToFile(inventory.products);
                    System.out.println("Dodano produkt " + product.getName());
                    break;
                case 3:
                    System.out.print("Podaj nazwę produktu do usunięcia: ");
                    String productName = scanner.nextLine();
                    Product productToRemove = new Product(productName, 0);
                    inventory.removeProduct(productToRemove);
                    fileManager.writeInventoryToFile(inventory.products);
                    System.out.println("Usunięto produkt " + productToRemove.getName());
                    break;
                case 0:
                    fileManager.writeInventoryToFile(inventory.products);
                    System.out.println("Wyjście z programu.");
                    return;
                default:
                    System.out.println("Niepoprawny wybór.");
                    break;
            }
        }
    }
}