package crouskiebackoffice.model;

import java.util.Date;


public class ProductBought {
    private int id;
    private ProductColorSize productColorSize;
    private int numOrder;
    private Date date;

    public ProductBought(int id, ProductColorSize productColorSize, int numOrder, Date date) {
        this.id = id;
        this.productColorSize = productColorSize;
        this.numOrder = numOrder;
        this.date = date;
    }    
}
