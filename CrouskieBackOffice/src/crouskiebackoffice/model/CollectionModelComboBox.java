package crouskiebackoffice.model;

import java.util.List;
import javax.swing.DefaultComboBoxModel;


public class CollectionModelComboBox extends DefaultComboBoxModel<Collection> {

    List<Collection> collectionList;

    public CollectionModelComboBox(List<Collection> collectionList) {
        System.out.println(collectionList);
        this.collectionList = collectionList;
        this.collectionList.add(new Collection(-1, ""));//un collection vide pour lui attribué acune collection
        
        addAll(collectionList);
        
    }

}
