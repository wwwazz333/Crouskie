package crouskiebackoffice;

import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.DAOProduct;
import java.sql.SQLException;

public class CrouskieBackOffice {

    public static void main(String[] args) {
        try {
            DAOProduct prod = new DAOProduct();
            for (var pr : prod.getProducts()) {
                System.out.println(pr);
            }
            prod.setNameOf(1, "newName");
            for (var pr : prod.getProducts()) {
                System.out.println(pr);
            }

            ConnectionDB.getInstance().close();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

}
