package crouskiebackoffice.controle;

import crouskiebackoffice.model.DataProduct;
import crouskiebackoffice.model.ModelVisualisationProduct;
import crouskiebackoffice.model.Observer;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.view.EditProduct;

/**
 *
 * @author wwwazz
 */
public class ControllerProductTable implements Observer {

    ModelVisualisationProduct modelVisualisationProduct;

    public ControllerProductTable() {
        DataProduct.getInstance().registerObserver(this);
        modelVisualisationProduct = new ModelVisualisationProduct(this, DataProduct.getInstance().getData());
        
    }

    public ModelVisualisationProduct getModelVisualisationProduct() {
        return modelVisualisationProduct;
    }

    public void goToEdit(Product currentProduct) {
        Navigator.getInstance().goTo(new EditProduct(currentProduct), "editProduct");
    }

    @Override
    public void update() {
        modelVisualisationProduct.setData(DataProduct.getInstance().getData());
    }

}
