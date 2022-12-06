package crouskiebackoffice.model;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.dao.DAOCollection;
import crouskiebackoffice.view.CollectionSelectionView;
import crouskiebackoffice.view.MainWindow;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

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
            Collection collectionToSelect = new Collection(-1, null);
            new CollectionSelectionView(MainWindow.instance, true).setVisible(true);
            if (collectionToSelect.getId() != -1) {
                setSelectedItem(collectionToSelect);
            }
        });

    }

}
