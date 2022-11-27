package crouskiebackoffice.model;

import crouskiebackoffice.model.dao.DAOProduct;
import java.util.LinkedList;
import java.util.List;

public class DataProduct extends Notifier {

    private static DataProduct instance;

    DAOProduct daoProduct;
    List<Product> data = new LinkedList<>();

    public static DataProduct getInstance() throws Exception {
        if (instance == null) {
            instance = new DataProduct();
        }
        return instance;
    }

    private DataProduct() throws Exception {
        daoProduct = new DAOProduct();
        notif();
    }

    @Override
    public void notif() throws Exception  {
        data = daoProduct.getAllData("nameprod");

        super.notif();
    }

    public List<Product> getData() {
        return data;
    }
}
