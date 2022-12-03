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

public class ControllerEditProduct {

    private Product product;
    private DAOCollection daoCollection;
    private DAOProduct daoProduct;
    private EditProduct editProduct;

    public ControllerEditProduct(EditProduct editProduct, Product product) {
        this.editProduct = editProduct;
        this.product = product;
        daoCollection = new DAOCollection();
        daoProduct = new DAOProduct();

    }

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
            product.setCollection((Collection) comboBoxModel.getSelectedItem());

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

    public AddDelListIem getAddDelListIem() {
        return new AddDelListIem() {
            @Override
            public void add(JList jlist) {
                if (jlist.getModel() instanceof DynamicListModel) {
                    DynamicListModel model = (DynamicListModel) jlist.getModel();
                    model.addItem();
                }
            }

            @Override
            public void del(JList jlist) {
                if (jlist.getSelectedIndex() != -1 && jlist.getModel() instanceof DynamicListModel) {
                    DynamicListModel model = (DynamicListModel) jlist.getModel();
                    model.remove(jlist.getSelectedIndex());
                }
            }
        };
    }

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
