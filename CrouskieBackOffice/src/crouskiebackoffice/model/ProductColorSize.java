package crouskiebackoffice.model;

public class ProductColorSize {

    Product product;
    Color color;
    ClothSize size;
    int quantity;

    public ProductColorSize(Product product, Color color, ClothSize size, int quantity) {
        this.product = product;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Color getColor() {
        return color;
    }

    public ClothSize getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }
    
    

    @Override
    public String toString() {
        return product.toString() + "\t" + color.toString() + "\t" + size.toString() + "\t" + quantity;
    }

}
