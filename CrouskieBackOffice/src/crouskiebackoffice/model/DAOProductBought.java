package crouskiebackoffice.model;

import crouskiebackoffice.exceptions.CantEditException;
import java.sql.SQLException;
import java.util.HashMap;

public class DAOProductBought extends DAO<ProductBought> {

    /**
     * Can only insert because, it's not allowed to edit command of customers
     *
     * @param obj the object to insert into the table
     * @return if the action completed
     * @throws SQLException an Exception may happen due to the request or the incapability to edit values
     */
    @Override
    public Boolean insertOrUpdate(ProductBought obj) throws SQLException {
        if (exist(obj)) {
            throw new CantEditException("Command of customer");
        } else {
            Object[] args = {obj.getNumOrder(), obj.getProductColorSize().getProduct().getId(), obj.getProductColorSize().getColor().getName(),
                obj.getProductColorSize().getSize().getId(), obj.getProductColorSize().getQuantity()};
            return super.execute("INSERT INTO PRODUCTBOUGHT (numorder, idprod, namecolor, idsize, quantitybought) VALUES (?, ?, ?, ?, ?)", args) == 1;
        }
    }

    @Override
    public Boolean exist(ProductBought obj) throws SQLException {
        return obj.getId() != -1;
    }

    @Override
    protected ProductBought parseData(HashMap<String, Object> obj) {
        return new ProductBought((int) obj.get("idpp"),
                new ProductColorSize(
                        new Product((int) obj.get("idprod"), obj.get("nameprod").toString(), obj.get("descriptionprod").toString(), (float) obj.get("priceprod"), null),
                        new Color(obj.get("namecolor").toString()),
                        new ClothSize((int) obj.get("idsize"), obj.get("namesize").toString()), (int) obj.get("quantitybought")),
                (int) obj.get("numorder"), (java.sql.Timestamp) obj.get("dateorder"));
    }

    @Override
    protected String getTableName() {
        return "PRODUCTBOUGHT natural join CMD natural join PRODUCT natural join CLOTH_SIZE natural join COLOR";
    }

}
