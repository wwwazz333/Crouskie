package crouskiebackoffice;

import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.DAOProduct;
import crouskiebackoffice.model.Product;
import java.sql.SQLException;

public class CrouskieBackOffice {

    public static void main(String[] args) {
        try {
            DAOProduct prod = new DAOProduct();
            for (var pr : prod.getProducts()) {
                System.out.println(pr);
            }

            Product product = new Product("name Product isnerted", "sldfkj", 1.5f);
            prod.insertOrUpdate(product);
            System.out.println("");
            System.out.println("");
            
            for (var pr : prod.getProducts()) {
                System.out.println(pr);
            }
            
            System.out.println("j'ai resussi tt seul : " + product.getId());

            ConnectionDB.getInstance().close();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

}
