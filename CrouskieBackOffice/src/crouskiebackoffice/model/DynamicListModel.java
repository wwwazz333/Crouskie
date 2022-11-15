package crouskiebackoffice.model;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

public class DynamicListModel extends DefaultListModel<Object> {

    public static final String ajoutLabel = "Ajouté...";

    public DynamicListModel(List<? extends HasName> namedList, DAO daoForAdding) {
        for (HasName named : namedList) {
            addElement(named.getName());
        }
//        JComboBox comboBoxAdding = new javax.swing.JComboBox<>();
//
//        comboBoxAdding.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"...", "Item 2", "Item 3", "Item 4"}));
//        addElement(comboBoxAdding);
        

        addElement(DynamicListModel.ajoutLabel);
    }
    
    

}
