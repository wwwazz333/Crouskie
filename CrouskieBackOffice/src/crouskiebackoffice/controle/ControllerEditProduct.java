package crouskiebackoffice.controle;

import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.CollectionModelComboBox;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.DAOClothSize;
import crouskiebackoffice.model.DAOCollection;
import crouskiebackoffice.model.DAOColor;
import crouskiebackoffice.model.DAOProduct;
import crouskiebackoffice.model.DAOTag;
import crouskiebackoffice.model.DynamicListModel;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.Tag;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author wwwazz
 */
public class ControllerEditProduct {

    private Product product;
    private DAOCollection daoCollection;
    private DAOProduct daoProduct;
    private DynamicListModel<Tag> tagsListModel;
    private DynamicListModel<Color> colorsListModel;
    private DynamicListModel<ClothSize> sizesListModel;
    private DefaultComboBoxModel collectionComboBoxModel;

    public ControllerEditProduct(Product product) {
        this.product = product;
        daoCollection = new DAOCollection();
        daoProduct = new DAOProduct();

        try {
            collectionComboBoxModel = new CollectionModelComboBox(daoCollection.getAllData());
        } catch (SQLException ex) {
            Logger.getLogger(ControllerEditProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        tagsListModel = new DynamicListModel<>(product.getTags(), new DAOTag());
        colorsListModel = new DynamicListModel<>(product.getExistingColor(), new DAOColor());
        sizesListModel = new DynamicListModel<>(product.getExistingSize(), new DAOClothSize());
    }

    public void save(String name, String description, String price) throws NumberFormatException, SQLException {

        product.setPrice(Float.parseFloat(price));
        product.setName(name);
        product.setDescription(description);
        product.setCollection((Collection) collectionComboBoxModel.getSelectedItem());

        product.setExistingColor(colorsListModel.getNamedList());
        product.setExistingSize(sizesListModel.getNamedList());
        product.setTags(tagsListModel.getNamedList());

        System.out.println(daoProduct.insertOrUpdate(product));
    }

    public ComboBoxModel getCollectionComboBox() {
        collectionComboBoxModel.setSelectedItem(product.getCollection());
        return collectionComboBoxModel;
    }

    public MouseListener getMouseListenerForTagsList() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                Object obj = tagsListModel.get(index);
                if (obj == DynamicListModel.ajoutLabel) {
                    tagsListModel.add(tagsListModel.getSize() - 1,
                            (new AddingController<Tag>(new DAOTag())).newValue());
                }
            }
        };
    }

    public MouseListener getMouseListenerForSizesList() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                Object obj = sizesListModel.get(index);
                if (obj == DynamicListModel.ajoutLabel) {
                    ClothSize clothSize = (new AddingController<ClothSize>(new DAOClothSize())).newValue();
                    System.out.println(clothSize);
                    sizesListModel.add(sizesListModel.getSize() - 1, clothSize);
                }
            }
        };
    }

    public MouseListener getMouseListenerForColorsList() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                Object obj = colorsListModel.get(index);
                if (obj == DynamicListModel.ajoutLabel) {
                    colorsListModel.add(colorsListModel.getSize() - 1,
                            (new AddingController<Color>(new DAOColor())).newValue());
                }
            }
        };
    }

    public DefaultListModel getTagsListModel() {
        return tagsListModel;
    }

    public DefaultListModel getColorsListModel() {
        return colorsListModel;
    }

    public DefaultListModel getSizesListModel() {
        return sizesListModel;
    }

}
