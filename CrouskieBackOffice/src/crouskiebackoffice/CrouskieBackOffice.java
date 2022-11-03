package crouskiebackoffice;

import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.DAOProductBought;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.ProductBought;
import crouskiebackoffice.model.ProductColorSize;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CrouskieBackOffice {

    public static void main(String[] args) {
        try {
            for (var pr : new DAOProductBought().getAllData("idpp")) {
                System.out.println(pr);
            }

            new DAOProductBought().insertOrUpdate(
                    new ProductBought(
                            new ProductColorSize(
                                    new Product(1, "", "", 0f), new Color("rouge"), new ClothSize(2, "S"), 55),
                            1,
                            new Timestamp(0))
            );
            ConnectionDB.getInstance().close();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

}
