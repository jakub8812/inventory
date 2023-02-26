import java.util.List;

/** Klasa reprezentująca magazyn produktów,
 * których stan jest przechowywany w postaci listy obiektów klasy Product. */
public class Inventory {
    public List<Product> products;

    public Inventory(List<Product> products) {
        this.products = products;
    }

    /** Metoda dodająca produkt do magazynu. Jeśli produkt o podanej nazwie już istnieje w magazynie,
     * to jego ilość jest zwiększana o ilość nowo dodanego produktu.
     * @param product produkt, który ma zostać dodany do magazynu */
    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                return;
            }
        }   products.add(product);
    }

    /** Metoda usuwająca produkt o podanej nazwie z magazynu.
     * Jeśli produkt o podanej nazwie istnieje w magazynie, to jego ilość jest zmniejszana o podaną wartość.
     * Jeśli ilość produktu wynosi po tej operacji 0, to produkt jest usuwany z magazynu.
     * Jeśli produkt o podanej nazwie nie istnieje w magazynie, to jest wyświetlany odpowiedni komunikat.
     * @param productName nazwa produktu, który ma zostać usunięty z magazynu
     * @param quantityToRemove ilość produktu, która ma zostać usunięta z magazynu */
    public void removeProduct(String productName, int quantityToRemove) {
        int productIndex = FindProductIndex(productName);
        if (productIndex != -1) {
            Product product = products.get(productIndex);
            if (product.getQuantity() > quantityToRemove) {
                product.setQuantity(product.getQuantity() - quantityToRemove);
                FileManager.writeInventoryToFile(products);
                System.out.println(quantityToRemove + " szt. produktu " + productName + " zostało usuniętych z magazynu.");
            } else {
                products.remove(productIndex);
                FileManager.writeInventoryToFile(products);
                System.out.println("Produkt " + productName + " został usunięty z magazynu.");
            }
        } else {
            System.out.println("Nie ma produktu o nazwie " + productName + " w magazynie.");
        }
    }

    /** Metoda wyświetlająca stan magazynu na standardowym wyjściu.
     * Dla każdego produktu wyświetlana jest jego nazwa oraz ilość. */
    public void printInventory() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    /** Metoda wyszukująca produkt o podanej nazwie w liście produktów i zwracająca jego indeks.
    * Jeśli produkt o podanej nazwie nie istnieje, to zwracana jest wartość -1.**/
    public int FindProductIndex(String productName) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(productName)) {
                return i;
            }
        }
        return -1; // zwrócenie wartości -1 oznacza, że produkt nie został znaleziony
    }
}