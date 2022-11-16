package crouskiebackoffice.controle;

import crouskiebackoffice.model.DAO;
import crouskiebackoffice.model.DAOProduct;
import crouskiebackoffice.model.ModelVisualisationProduct;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.view.EditProduct;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author wwwazz
 */
public class ControllerProductTable {

    ModelVisualisationProduct modelVisualisationProduct;
    DAO dao;

    public ControllerProductTable() {
        dao = new DAOProduct();
        try {
            modelVisualisationProduct = new ModelVisualisationProduct(this, dao.getAllData("nameprod"));
        } catch (SQLException ex) {
            modelVisualisationProduct = new ModelVisualisationProduct(this, new LinkedList<>());
        }
    }

    public ModelVisualisationProduct getModelVisualisationProduct() {
        return modelVisualisationProduct;
    }

    public void goToEdit(Product currentProduct) {
        Navigator.getInstance().goTo(new EditProduct(currentProduct), "editProduct");
    }

}
