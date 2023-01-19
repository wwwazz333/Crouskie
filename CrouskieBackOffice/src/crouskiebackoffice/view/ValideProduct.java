package crouskiebackoffice.view;

import crouskiebackoffice.model.Product;

public class ValideProduct extends EditProduct {

    public ValideProduct(Product prod) {
        super(prod);
        controller.setForceInsertWheneSave(true);
    }

    @Override
    protected void valide() {
        super.valide();
        product.setId(-1);
    }
    
    

}
