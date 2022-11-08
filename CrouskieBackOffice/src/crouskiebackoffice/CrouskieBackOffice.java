package crouskiebackoffice;

import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.DAOTag;
import java.sql.SQLException;

public class CrouskieBackOffice {

    public static void main(String[] args) {
        try {
            DAOTag dao = new DAOTag();
            for (var pr : dao.getAllData()) {
                System.out.println(pr);
            }

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
