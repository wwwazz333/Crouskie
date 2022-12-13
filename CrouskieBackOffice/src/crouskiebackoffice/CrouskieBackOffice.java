package crouskiebackoffice;

import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.dao.DAO;
import crouskiebackoffice.model.dao.DAOStock;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class CrouskieBackOffice {

  

    public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException, URISyntaxException, InterruptedException, SQLException, Exception {
        DAO dao = new DAOStock();
        for (var c : dao.getAllData()) {
            System.out.println(c);
        }
    }

}
