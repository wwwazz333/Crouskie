package crouskiebackoffice.controle;

import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.dao.DAOCollection;
import crouskiebackoffice.model.dao.DAOProduct;
import crouskiebackoffice.model.listmodel.DynamicListModel;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.ProductManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.JList;

public class ControllerEditProduct {

    private Product product;
    private DAOCollection daoCollection;
    private DAOProduct daoProduct;

    public ControllerEditProduct(Product product) {
        this.product = product;
        daoCollection = new DAOCollection();
        daoProduct = new DAOProduct();

    }

    public void save(String name, String description, String price, ComboBoxModel comboBoxModel,
            DynamicListModel colorsListModel,
            DynamicListModel sizesListModel,
            DynamicListModel tagsListModel) throws NumberFormatException, SQLException {

        product.setPrice(Float.parseFloat(price));
        product.setName(name);
        product.setDescription(description);
        product.setCollection((Collection) comboBoxModel.getSelectedItem());

        product.setExistingColor(colorsListModel.getData());
        product.setExistingSize(sizesListModel.getData());
        product.setTags(tagsListModel.getData());

        ProductManager.getInstance().save(product);
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

}
