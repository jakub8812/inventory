import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private static String fileName = "inventory.txt";

    public FileManager() {
    }

    public static void setFileName(String fileName) {
        FileManager.fileName = fileName;
    }

    public static List<Product> readInventoryFromFile() {
        List<Product> products = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                Product product = new Product(name, quantity);
                products.add(product);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Nie udało się odczytać stanu magazynu z pliku " + fileName);
        }
        return products;
    }

    public static void writeInventoryToFile(List<Product> products) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getQuantity() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać stanu magazynu do pliku " + fileName);
        }
    }
}