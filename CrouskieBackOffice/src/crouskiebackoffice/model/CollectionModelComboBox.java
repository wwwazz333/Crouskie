package crouskiebackoffice.model;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.dao.DAOCollection;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class CollectionModelComboBox extends DefaultComboBoxModel {

    public CollectionModelComboBox(Product product) {

        ErrorHandeler.getInstance().exec(() -> {
            List<Object> objectList = new LinkedList<>(new DAOCollection().getAllData());
            objectList.add(new Collection(-1, ""));//un collection vide pour lui attribué acune collection

            addAll(objectList);

            setSelectedItem(product.getCollection());
        });

    }

}
