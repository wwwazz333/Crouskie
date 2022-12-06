package crouskiebackoffice.model;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.dao.DAO;
import crouskiebackoffice.model.dao.DAOCollection;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class CollectionModelComboBox extends DefaultComboBoxModel {

    public CollectionModelComboBox(Product product, JButton addingBtn) {

        ErrorHandeler.getInstance().exec(() -> {
            List<Object> objectList = new LinkedList<>(new DAOCollection().getAllData());
            objectList.add(new Collection(-1, ""));//un collection vide pour lui attribué acune collection

            addAll(objectList);

            setSelectedItem(product.getCollection());
            return true;
        });
        
        
        addingBtn.addActionListener((ae) -> {
            String nameNewCollection = JOptionPane.showInputDialog("Nom de la nouvelle collection");
            if (nameNewCollection != null){
                
                DAO dao = new DAOCollection();
//                dao.insertOrUpdate(new Collection ())
            }
        });

    }

}
