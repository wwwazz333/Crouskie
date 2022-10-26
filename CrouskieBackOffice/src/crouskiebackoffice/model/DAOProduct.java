package crouskiebackoffice.model;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class DAOProduct extends DAO {

    private static final String tableName = "PRODUCT";

    public Product[] getProducts() throws SQLException, NumberFormatException {
        List<Object[]> res = super.selectAll("SELECT * FROM " + tableName, null);
        Product[] allProducts = new Product[res.size()];

        int i = 0;
        for (Object[] values : res) {
            allProducts[i++] = (new Product(Integer.parseInt(values[0].toString()), values[1].toString(),
                    values[2].toString(), Float.parseFloat(values[3].toString())));
        }

        return allProducts;
    }

    public Boolean setNameOf(Product product, String newName) throws SQLException {
        return setNameOf(product.getId(), newName);
    }

    public Boolean setNameOf(int idProduct, String newName) throws SQLException {
        Object[] args = {newName, idProduct};
        return super.execute("UPDATE " + tableName + " SET nameprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean setDescriptionOf(Product product, String newDescription) throws SQLException {
        return setDescriptionOf(product.getId(), newDescription);
    }

    public Boolean setDescriptionOf(int idProduct, String newDescription) throws SQLException {
        Object[] args = {newDescription, idProduct};
        return super.execute("UPDATE " + tableName + " SET descriptionprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean setPriceOf(Product product, Float newPrice) throws SQLException {
        return setPriceOf(product.getId(), newPrice);
    }

    public Boolean setPriceOf(int idProduct, Float newPrice) throws SQLException {
        Object[] args = {newPrice, idProduct};
        return super.execute("UPDATE " + tableName + " SET priceprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean exist(Product product) throws SQLException {
        Object[] args = {product.getId()};
        return ((int) super.selectAll("SELECT count(idprod) FROM " + tableName + " WHERE idprod = ?", args).get(0)[0]) == 1;
    }

    public int getIdOf(Product product) throws SQLException {
        int id = -1;
        Object[] args = {product.getName(), product.getDescription(), product.getPrice()};
        List<Object[]> liste = super.selectAll("SELECT idprod FROM " + tableName + " WHERE nameprod = ? and descriptionprod = ? and priceprod = ?", args);
        if (!liste.isEmpty()) {
            id = ((int) liste.get(0)[0]);
        }
        return id;
    }

    private int getLastIdInserted() throws SQLException {
        return ((BigInteger) super.selectAll("SELECT LAST_INSERT_ID(IDPROD) from PRODUCT order by LAST_INSERT_ID(IDPROD) desc limit 1;", null).get(0)[0]).intValue();
    }

    public Boolean insertOrUpdate(Product product) throws SQLException {
        int idExistant = product.getId();
        if (idExistant != -1 || (idExistant = getIdOf(product)) != -1) {
            product.setId(idExistant);
            Object[] args = {product.getName(), product.getDescription(), product.getPrice(), product.getId()};
            return super.execute("UPDATE " + tableName + " SET nameprod = ?, descriptionprod = ?, priceprod = ? WHERE idprod = ?", args) == 1;
        } else {
            Object[] args = {product.getName(), product.getDescription(), product.getPrice()};
            Boolean res = super.execute("INSERT INTO " + tableName + " (nameprod, descriptionprod, priceprod) VALUES (?, ?, ?)", args) == 1;
            product.setId(getLastIdInserted());
            return res;
        }
    }
}
