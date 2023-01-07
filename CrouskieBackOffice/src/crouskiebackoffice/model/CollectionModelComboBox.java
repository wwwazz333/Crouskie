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

/**
 * Classe permettant de gérer les collections dans un combobox. Cette classe
 * permet d'ajouter une collection au moyen d'un bouton. Elle met également à
 * jour les données du combobox lorsque le produit est modifié.
 */
public class CollectionModelComboBox extends DefaultComboBoxModel {

    /**
     * Construit un nouveau modèle de combobox de collections en utilisant les
     * données du produit donné. Le bouton d'ajout de collection est également
     * enregistré pour pouvoir être utilisé lors de l'ajout d'une nouvelle
     * collection.
     *
     * @param product Le produit associé au combobox
     * @param addingBtn Le bouton d'ajout de collection
     */
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

    /**
     *
     * Met à jour le modèle de la combobox en récupérant toutes les collections
     * disponibles depuis la base de données.
     *
     * Sélectionne la collection associée au produit passé en paramètre.
     *
     * @param product Le produit dont on veut sélectionner la collection
     * associée.
     *
     * @throws SQLException 
     *
     * @throws ErrorHandelabelAdapter
     */
    private void update(Product product) throws SQLException, ErrorHandelabelAdapter {

        removeAllElements();
        List<Object> objectList = new LinkedList<>(new DAOCollection().getAllData());
        objectList.add(new Collection(-1, ""));//un collection vide pour lui attribué acune collection

        addAll(objectList);

        setSelectedItem(product.getCollection());
    }
}
