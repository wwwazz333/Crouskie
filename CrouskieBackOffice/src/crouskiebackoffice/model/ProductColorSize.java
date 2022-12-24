package crouskiebackoffice.model;

public class ProductColorSize {

    private Product product;
    private Color color;
    private ClothSize size;
    private Integer quantity;

    public ProductColorSize(Product product, Color color, ClothSize size, Integer quantity) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    

    @Override
    public String toString() {
        return product.toString() + "\t" + color.toString() + "\t" + size.toString() + "\t" + quantity;
    }

}
