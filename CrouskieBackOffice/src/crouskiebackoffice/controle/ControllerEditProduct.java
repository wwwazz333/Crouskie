package crouskiebackoffice.controle;

import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.Picture;
import crouskiebackoffice.model.dao.DAOCollection;
import crouskiebackoffice.model.dao.DAOProduct;
import crouskiebackoffice.model.listmodel.DynamicListModel;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.ProductManager;
import crouskiebackoffice.view.EditProduct;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JList;

/**
 * Contrôleur utilisé pour la modification d'un produit dans l'application.
 */
public class ControllerEditProduct {

    /**
     * Le produit à modifier.
     */
    private Product product;
    /**
     * DAO pour les collections.
     */
    private DAOCollection daoCollection;
    /**
     * DAO pour les produits.
     */
    private DAOProduct daoProduct;
    /**
     * Vue de modification de produit.
     */
    private EditProduct editProduct;

    /**
     * Crée un nouveau contrôleur de modification de produit avec la vue de
     * modification de produit et le produit à modifier.
     *
     * @param editProduct la vue de modification de produit
     * @param product le produit à modifier
     */
    public ControllerEditProduct(EditProduct editProduct, Product product) {
        this.editProduct = editProduct;
        this.product = product;
        daoCollection = new DAOCollection();
        daoProduct = new DAOProduct();

    }

    /**
     * Sauvegarde les modifications apportées au produit.
     *
     * @param name le nom du produit
     * @param description la description du produit
     * @param price le prix du produit
     * @param enVente indique si le produit est en vente
     * @param comboBoxModel le modèle du ComboBox de sélection de collection
     * @param colorsListModel le modèle de la liste de couleurs
     * @param sizesListModel le modèle de la liste de tailles
     * @param tagsListModel le modèle de la liste de tags
     * @param pictures la liste d'images du produit
     * @return true si la sauvegarde a réussi, false sinon
     * @throws NumberFormatException si le prix du produit n'est pas valide
     * @throws SQLException si une erreur SQL se produit lors de la sauvegarde
     */
    public boolean save(String name, String description, String price,
            boolean enVente,
            ComboBoxModel comboBoxModel,
            DynamicListModel colorsListModel,
            DynamicListModel sizesListModel,
            DynamicListModel tagsListModel,
            List<Picture> pictures) throws NumberFormatException, SQLException {
        if (!name.isBlank() && !description.isBlank() && !price.isBlank()) {
            product.setPrice(Float.parseFloat(price.replaceAll(",", ".")));
            product.setName(name);
            product.setDescription(description);

            Collection currCollection = (Collection) comboBoxModel.getSelectedItem();
            if (new DAOCollection().exist(currCollection)) {
                product.setCollection(currCollection);
            } else {
                product.setCollection(null);
            }

            product.setEnVente(enVente);

            product.setExistingColor(colorsListModel.getData());
            product.setExistingSize(sizesListModel.getData());
            product.setTags(tagsListModel.getData());

            product.setPictures(pictures);

            return ErrorHandeler.getInstance().exec(() -> {
                return ProductManager.getInstance().save(product);
            });

        }
        return false;
    }

    /**
     * Retourne une implémentation de l'interface AddDelListIem.
     *
     * @return une implémentation de l'interface AddDelListIem
     */
    public AddDelListIem getAddDelListItem() {
        return new AddDelListIem() {
            @Override
            public void add(JList jlist) {
                if (jlist.getModel() instanceof DynamicListModel model) {
                    model.addItem();
                }
            }

            @Override
            public void del(JList jlist) {
                if (jlist.getSelectedIndex() != -1 && jlist.getModel() instanceof DynamicListModel model) {
                    model.remove(jlist.getSelectedIndex());
                }
            }
        };
    }

    /**
     * Remet à zéro tous les champs de la vue de modification de produit.
     */
    public void clearAll() {
        editProduct.getNameInput().setText("");
        editProduct.getDescriptionInput().setText("");
        editProduct.getPriceInput().setText("");

        editProduct.getCollectionComboBox().setSelectedIndex(editProduct.getCollectionComboBox().getModel().getSize() - 1);//selection le dernier (vide)

        DynamicListModel model = (DynamicListModel) editProduct.getListColor().getModel();
        model.removeAllElements();
        model = (DynamicListModel) editProduct.getListColor().getModel();
        model.removeAllElements();
        model = (DynamicListModel) editProduct.getListTag().getModel();
        model.removeAllElements();

    }

}
