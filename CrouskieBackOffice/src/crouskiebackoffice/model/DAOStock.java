package crouskiebackoffice.model;

import java.sql.SQLException;
import java.util.HashMap;

public class DAOStock extends DAO<ProductColorSize> {

    @Override
    protected String getTableName() {
        return "STOCKED natural join PRODUCT natural join COLOR natural join CLOTH_SIZE";
    }

    /**
     *
     * @param productColorSize the product has been stocked or his stock has been updated
     * @return if action completed
     * @throws SQLException SQLException an Exception may happen due to the request (ex : if the product doesn't exist)
     */
    @Override
    public Boolean insertOrUpdate(ProductColorSize productColorSize) throws SQLException {
        if (exist(productColorSize)) {
            Object[] args = {productColorSize.getQuantity(), productColorSize.getProduct().getId(), productColorSize.getColor().getName(), productColorSize.getSize().getId()};

            return super.execute("UPDATE " + getTableName() + " SET quantitystocked = ? WHERE idprod = ? and namecolor = ? and idsize = ?", args) == 1;

        } else {
            Object[] args = {productColorSize.getProduct().getId(), productColorSize.getColor().getName(), productColorSize.getSize().getId(), productColorSize.getQuantity()};
            return super.execute("INSERT INTO " + getTableName() + "(idprod, namecolor, idsize, quantitystocked) VALUES (?, ?, ?, ?)", args) == 1;
        }
    }

    @Override
    protected ProductColorSize parseData(HashMap<String, Object> obj) {
        return new ProductColorSize(
                new Product((int) obj.get("idprod"), obj.get("nameprod").toString(), obj.get("descriptionprod").toString(), (float) obj.get("priceprod")),
                new Color(obj.get("namecolor").toString()),
                new ClothSize((int) obj.get("idsize"), obj.get("namesize").toString()), (int) obj.get("quantitystocked"));
    }

    @Override
    public Boolean exist(ProductColorSize obj) {
        return obj.getColor() != null && obj.getColor().getName() != null
                && obj.getProduct() != null && obj.getProduct().getId() != -1
                && obj.getSize() != null && obj.getSize().getId() != -1;
    }

}
