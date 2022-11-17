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

        product.setExistingColor(colorsListModel.getNamedList());
        product.setExistingSize(sizesListModel.getNamedList());
        product.setTags(tagsListModel.getNamedList());

        ProductManager.getInstance().save(product);
    }

    public MouseListener getMouseListenerForList() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (list.getModel() instanceof DynamicListModel) {
                    DynamicListModel model = (DynamicListModel) list.getModel();
                    int index = list.locationToIndex(evt.getPoint());
                    System.out.println(model);
                    Object obj = model.get(index);
                    if (obj == DynamicListModel.ajoutLabel) {
                        model.add();
                    }
                }

            }
        };
    }

}
