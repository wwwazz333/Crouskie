package crouskiebackoffice.model;

import crouskiebackoffice.model.dao.DAOStock;
import java.util.LinkedList;
import java.util.List;

public class DataStock extends Notifier {

    private static DataStock instance;

    DAOStock dao;
    List<ProductColorSize> data = new LinkedList<>();

    public static DataStock getInstance() throws Exception {
        if (instance == null) {
            instance = new DataStock();
        }
        return instance;
    }

    private DataStock() throws Exception {
        dao = new DAOStock();
        notif();
    }

    @Override
    public void notif() throws Exception {
        data = dao.getAllData("nameprod");

        super.notif();
    }

    public List<ProductColorSize> getData() {
        return data;
    }
}
