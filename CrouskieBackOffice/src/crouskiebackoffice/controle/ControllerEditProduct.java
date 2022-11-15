/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crouskiebackoffice.controle;

import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.DAOCollection;
import crouskiebackoffice.model.Product;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author wwwazz
 */
public class ControllerEditProduct {

    private Product product;
    private DAOCollection daoCollection;

    public ControllerEditProduct(Product product) {
        this.product = product;
        daoCollection = new DAOCollection();
    }

    public JComboBox getCollectionComboBox() throws SQLException {
        List<Collection> collectionList = daoCollection.getAllData();
        collectionList.add(new Collection(-1, ""));
        JComboBox jcb = new JComboBox(collectionList.toArray());
        jcb.setSelectedItem(product.getCollection());
        return jcb;
    }

}
