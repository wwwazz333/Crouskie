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

    @Override
    public String toString() {
        return product.toString() + "\t" + color.toString() + "\t" + size.toString() + "\t" + quantity;
    }

}
