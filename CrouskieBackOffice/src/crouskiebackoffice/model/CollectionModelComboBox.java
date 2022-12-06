package crouskiebackoffice.model;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.dao.DAOCollection;
import crouskiebackoffice.view.CollectionSelectionView;
import crouskiebackoffice.view.MainWindow;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class CollectionModelComboBox extends DefaultComboBoxModel {

    public CollectionModelComboBox(Product product, JButton addingBtn) {

        ErrorHandeler.getInstance().exec(() -> {
            update(product);
            return true;
        });

        addingBtn.addActionListener((ae) -> {
            ErrorHandeler.getInstance().exec(() -> {
                Collection collectionToSelect = new Collection(-1, null);
                new CollectionSelectionView(MainWindow.instance, true, collectionToSelect).setVisible(true);
                update(product);
                if (collectionToSelect.getId() != -1) {
                    setSelectedItem(collectionToSelect);
                }
                System.out.println(collectionToSelect);
                return true;
            });

        });

    }

    private void update(Product product) throws SQLException, ErrorHandelabelAdapter {
        
        removeAllElements();
        List<Object> objectList = new LinkedList<>(new DAOCollection().getAllData());
        objectList.add(new Collection(-1, ""));//un collection vide pour lui attribué acune collection

        addAll(objectList);

        setSelectedItem(product.getCollection());
    }

}
