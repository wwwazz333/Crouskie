package crouskiebackoffice.model;

import java.sql.SQLException;
import java.util.HashMap;

public class DAOProduct extends DAO<Product> {

    @Override
    protected String getTableName() {
        return "PRODUCT";
    }

    @Override
    protected Product parseData(HashMap<String, Object> obj) {
        return new Product((int) obj.get("idprod"), obj.get("nameprod").toString(), obj.get("descriptionprod").toString(), (float) obj.get("priceprod"));
    }

    public Boolean setNameOf(Product product, String newName) throws SQLException {
        return setNameOf(product.getId(), newName);
    }

    public Boolean setNameOf(int idProduct, String newName) throws SQLException {
        Object[] args = {newName, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET nameprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean setDescriptionOf(Product product, String newDescription) throws SQLException {
        return setDescriptionOf(product.getId(), newDescription);
    }

    public Boolean setDescriptionOf(int idProduct, String newDescription) throws SQLException {
        Object[] args = {newDescription, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET descriptionprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean setPriceOf(Product product, Float newPrice) throws SQLException {
        return setPriceOf(product.getId(), newPrice);
    }

    public Boolean setPriceOf(int idProduct, Float newPrice) throws SQLException {
        Object[] args = {newPrice, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET priceprod = ? WHERE idprod = ?", args) == 1;
    }

    @Override
    public Boolean insertOrUpdate(Product product) throws SQLException {
        if (exist(product)) {
            Object[] args = {product.getName(), product.getDescription(), product.getPrice(), product.getId()};
            return super.execute("UPDATE " + getTableName() + " SET nameprod = ?, descriptionprod = ?, priceprod = ? WHERE idprod = ?", args) == 1;
        } else {
            Object[] args = {product.getName(), product.getDescription(), product.getPrice()};
            return super.execute("INSERT INTO " + getTableName() + " (nameprod, descriptionprod, priceprod) VALUES (?, ?, ?)", args) == 1;
        }
    }

    @Override
    protected Boolean exist(Product product) {
        return product != null && product.getId() != -1;
    }
}
