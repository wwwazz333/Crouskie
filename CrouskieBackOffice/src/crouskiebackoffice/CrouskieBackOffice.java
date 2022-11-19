package crouskiebackoffice;

import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.dao.DAOProduct;
import java.sql.SQLException;

public class CrouskieBackOffice {

    public static void main(String[] args) {
        try {
            DAOProduct dao = new DAOProduct();
            for (Product pr : dao.getAllData()) {
                System.out.println(pr);
            }
            System.out.println(dao.remove(new Product(123, "", ", ", 1f, null)));

//            new DAOProductBought().insertOrUpdate(
//                    new ProductBought(
//                            new ProductColorSize(
//                                    new Product(1, "", "", 0f), new Color("rouge"), new ClothSize(2, "S"), 55),
//                            1,
//                            new Timestamp(0))
//            );
            ConnectionDB.getInstance().close();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

}
