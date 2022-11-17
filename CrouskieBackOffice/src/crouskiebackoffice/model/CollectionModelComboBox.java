package crouskiebackoffice.model;

import crouskiebackoffice.model.dao.DAOCollection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

public class CollectionModelComboBox extends DefaultComboBoxModel {

    public CollectionModelComboBox(Product product) {

        try {
            List<Object> objectList = new LinkedList<>(new DAOCollection().getAllData());
            objectList.add(new Collection(-1, ""));//un collection vide pour lui attribué acune collection

            addAll(objectList);

            setSelectedItem(product.getCollection());
        } catch (SQLException ex) {
            Logger.getLogger(CollectionModelComboBox.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
