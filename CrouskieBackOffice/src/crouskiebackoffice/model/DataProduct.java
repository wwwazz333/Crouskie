package crouskiebackoffice.model;

import crouskiebackoffice.model.dao.DAOProduct;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProduct extends Notifier {

    private static DataProduct instance;

    DAOProduct daoProduct;
    List<Product> data = new LinkedList<>();

    public static DataProduct getInstance() {
        if (instance == null) {
            instance = new DataProduct();
        }
        return instance;
    }

    private DataProduct() {
        daoProduct = new DAOProduct();
        notif();
    }

    @Override
    public void notif() {
        try {
            data = daoProduct.getAllData("nameprod");
        } catch (SQLException ex) {
            Logger.getLogger(DataProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        super.notif();
    }

    public List<Product> getData() {
        return data;
    }
}
