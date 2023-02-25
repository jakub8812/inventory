import java.util.List;

public class Inventory {
    public List<Product> products;

    public Inventory(List<Product> products) {
        this.products = products;
    }


    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                return;
            }
        }   products.add(product);
    }

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

    public void printInventory() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public int FindProductIndex(String productName) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(productName)) {
                return i;
            }
        }
        return -1; // zwrócenie wartości -1 oznacza, że produkt nie został znaleziony
    }
}