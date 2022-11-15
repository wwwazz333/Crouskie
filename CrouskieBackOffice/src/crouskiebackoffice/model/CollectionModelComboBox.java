package crouskiebackoffice.model;

import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class CollectionModelComboBox extends DefaultComboBoxModel {

    List<Object> objectList;

    public CollectionModelComboBox(List<Collection> collectionList) {
        this.objectList = new LinkedList<>(collectionList);
        this.objectList.add(new Collection(-1, ""));//un collection vide pour lui attribué acune collection

        addAll(this.objectList);

    }

}
