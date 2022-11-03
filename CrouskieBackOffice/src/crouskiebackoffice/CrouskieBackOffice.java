package crouskiebackoffice;

import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.DAO;
import crouskiebackoffice.model.DAOClothSize;
import crouskiebackoffice.model.DAOColor;
import crouskiebackoffice.model.DAOProduct;
import crouskiebackoffice.model.DAOStock;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.ProductColorSize;
import java.sql.SQLException;

public class CrouskieBackOffice {

    public static void main(String[] args) {
        try {
            DAO prod = new DAOProduct();
            for (var pr : prod.getAllData()) {
                System.out.println(pr);
            }
//
//            Product product = new Product(4, "name Product isnerted", "sldfkj", 424f);
//            prod.insertOrUpdate(product);
//            System.out.println("");
//            System.out.println("");

            for (var pr : new DAOClothSize().getAllData("idsize")) {
                System.out.println(pr);
            }

            for (var pr : new DAOColor().getAllData()) {
                System.out.println(pr);
            }

            for (var pr : new DAOStock().getAllData()) {
                System.out.println(pr);
            }

            System.out.println("");
            System.out.println(new DAOColor().exist(new Color("rouge")));
            System.out.println(new DAOColor().exist(new Color("rouges")));

            new DAOStock().insertOrUpdate(new ProductColorSize(new Product(1, "string", "des", 12f), new Color("rouge"), new ClothSize(2, "S"), 14));
            
            
            
            ConnectionDB.getInstance().close();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

}
