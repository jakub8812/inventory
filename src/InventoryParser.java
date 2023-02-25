import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryParser {
    public static List<Product> parse(Scanner scanner) {
        List<Product> products = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            products.add(new Product(name, quantity));
        }
        return products;
    }
}