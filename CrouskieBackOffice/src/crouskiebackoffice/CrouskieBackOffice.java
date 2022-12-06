package crouskiebackoffice;

import crouskiebackoffice.model.Collection;
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
        Collection c = new Collection(0, "non");
        test(c);
        System.out.println(c.getName());
    }

}
