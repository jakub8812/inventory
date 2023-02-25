import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public List<Product> readInventoryFromFile() {
        List<Product> products = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            products = InventoryParser.parse(scanner);
        } catch (IOException e) {
            // Plik nie istnieje, tworzymy nowy plik
            System.out.println("Plik " + fileName + " nie istnieje. Tworzę nowy plik.");
            try {
                FileWriter writer = new FileWriter(fileName);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Nie udało się utworzyć pliku " + fileName);
            }
        }
        return products;
    }

    public void writeInventoryToFile(List<Product> products) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getQuantity() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać stanu magazynu do pliku " + fileName);
        }
    }
}