package crouskiebackoffice;

import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.dao.DAOCollection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class CrouskieBackOffice {

    public static void test(Collection coll) {
        coll.setName("bricole");
    }

    public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException, URISyntaxException, InterruptedException, SQLException, Exception {
        DAOCollection dao = new DAOCollection();
        for (Collection c : dao.getAllData()) {
            System.out.println(c.getPathPicture());
        }
    }

}
