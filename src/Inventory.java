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

    public void removeProduct(Product product) {
        products.removeIf(p -> p.getName().equals(product.getName()));
    }

    public void printInventory() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}