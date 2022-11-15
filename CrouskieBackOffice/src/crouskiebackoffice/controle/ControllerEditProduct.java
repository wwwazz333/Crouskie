package crouskiebackoffice.controle;

import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.CollectionModelComboBox;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.DAOCollection;
import crouskiebackoffice.model.DAOProduct;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.Tag;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author wwwazz
 */
public class ControllerEditProduct {

    private Product product;
    private DAOCollection daoCollection;
    private DAOProduct daoProduct;
    private DefaultListModel tagsListModel, colorsListModel, SizeListModel;
    private DefaultComboBoxModel<Collection> collectionComboBoxModel;

    public ControllerEditProduct(Product product) {
        this.product = product;
        daoCollection = new DAOCollection();
        daoProduct = new DAOProduct();

        try {
            collectionComboBoxModel = new CollectionModelComboBox(daoCollection.getAllData());
        } catch (SQLException ex) {
            Logger.getLogger(ControllerEditProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        tagsListModel = new DefaultListModel();
        for (Tag tag : product.getTags()) {
            tagsListModel.addElement(tag.getName());
        }
        colorsListModel = new DefaultListModel();
        for (Color color : product.getExistingColor()) {
            colorsListModel.addElement(color.getName());
        }

        SizeListModel = new DefaultListModel();
        for (ClothSize size : product.getExistingSize()) {
            SizeListModel.addElement(size.getName());
        }
    }

    public void save(String name, String description, String price) throws NumberFormatException, SQLException {

        product.setPrice(Float.parseFloat(price));
        product.setName(name);
        product.setDescription(description);
        product.setCollection((Collection) collectionComboBoxModel.getSelectedItem());

        daoProduct.insertOrUpdate(product);
    }

    public ComboBoxModel getCollectionComboBox() {
        collectionComboBoxModel.setSelectedItem(product.getCollection());
        return collectionComboBoxModel;
    }

    public DefaultListModel getTagsListModel() {
        return tagsListModel;
    }

    public DefaultListModel getColorsListModel() {
        return colorsListModel;
    }

    public DefaultListModel getSizeListModel() {
        return SizeListModel;
    }

}
