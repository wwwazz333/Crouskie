package crouskiebackoffice.controle;

import crouskiebackoffice.model.DataProduct;
import crouskiebackoffice.model.ModelVisualisationProduct;
import crouskiebackoffice.model.Observer;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.view.EditProduct;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerProductTable implements Observer {

    ModelVisualisationProduct modelVisualisationProduct;

    public ControllerProductTable() {
        ErrorHandeler.getInstance().exec(() -> {
            DataProduct.getInstance().registerObserver(this);
            modelVisualisationProduct = new ModelVisualisationProduct(this, DataProduct.getInstance().getData());
        });

    }

    public ModelVisualisationProduct getModelVisualisationProduct() {
        if (modelVisualisationProduct == null) {
            return new ModelVisualisationProduct(this, new LinkedList<>());
        }
        return modelVisualisationProduct;
    }

    public void goToEdit(Product currentProduct) {
        Navigator.getInstance().goTo(new EditProduct(currentProduct), "editProduct");
    }

    @Override
    public void update() {
        ErrorHandeler.getInstance().exec(() -> {
            modelVisualisationProduct.setData(DataProduct.getInstance().getData());
        });

    }

}
