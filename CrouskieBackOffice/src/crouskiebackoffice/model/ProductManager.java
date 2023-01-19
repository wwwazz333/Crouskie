package crouskiebackoffice.model;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.dao.DAOProduct;
import java.sql.SQLException;

/**
 * Gestionnaire pour accesder au dao Produit.
 */
public class ProductManager {

    private static ProductManager instance;

    private DAOProduct daoProduct;

    private ProductManager() {
        daoProduct = new DAOProduct();
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public boolean save(Product product, boolean forceInsertWheneSave) throws SQLException, ErrorHandelabelAdapter {
        return daoProduct.insertOrUpdate(product, forceInsertWheneSave);
    }

}
