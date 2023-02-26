import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa FileManager zawiera jedno pole prywatne fileName,
 * które jest stałą nazwą pliku, do którego będą zapisywane dane.
 * Konstruktor klasy jest pusty, ponieważ nie jest potrzebny.**/
public class FileManager {
    private static final String fileName = "inventory.txt";

    public FileManager() {
    }

    /**
     * Metoda readInventoryFromFile() odczytuje stan magazynu z pliku o nazwie inventory.txt i zwraca listę produktów.
     * Wykorzystuje obiekt typu Scanner do odczytywania pliku linia po linii, następnie każdą linię dzieli na części za pomocą metody split(",").
     * Pierwsza część reprezentuje nazwę produktu, a druga - ilość produktu. Następnie tworzy obiekt Product i dodaje go do listy produktów.
     * W przypadku wystąpienia błędu podczas odczytu lub konwersji liczby, wyświetlany jest komunikat o błędzie.**/
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

    /**
     * Metoda writeInventoryToFile() zapisuje stan magazynu do pliku o nazwie inventory.txt.
     * Wykorzystuje obiekt typu FileWriter, który pozwala na zapisanie tekstu do pliku.
     * Następnie iteruje po wszystkich produktach i dla każdego z nich zapisuje jego nazwę oraz ilość oddzielone przecinkiem, a następnie dodaje znak nowej linii.
     * W przypadku wystąpienia błędu podczas zapisu, wyświetlany jest komunikat o błędzie.**/
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