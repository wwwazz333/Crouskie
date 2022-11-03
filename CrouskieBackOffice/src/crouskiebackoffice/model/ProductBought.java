package crouskiebackoffice.model;

import java.sql.Timestamp;

public class ProductBought {

    private int id;
    private ProductColorSize productColorSize;
    private int numOrder;
    private java.sql.Timestamp date;

    public ProductBought(int id, ProductColorSize productColorSize, int numOrder, java.sql.Timestamp date) {
        this.id = id;
        this.productColorSize = productColorSize;
        this.numOrder = numOrder;
        this.date = date;
    }

    public ProductBought(ProductColorSize productColorSize, int numOrder, java.sql.Timestamp date) {
        this.id = -1;
        this.productColorSize = productColorSize;
        this.numOrder = numOrder;
        this.date = date;
    }

        

    

    public int getId() {
        return id;
    }

    public ProductColorSize getProductColorSize() {
        return productColorSize;
    }

    public int getNumOrder() {
        return numOrder;
    }

    public Timestamp getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ProductBought{" + "id=" + id + ", productColorSize=" + productColorSize + ", numOrder=" + numOrder + ", date=" + date + '}';
    }

}
