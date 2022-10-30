package crouskiebackoffice.model;

import java.sql.SQLException;
import java.util.HashMap;

public class DAOStock extends DAO<ProductColorSize> {

    @Override
    protected String getTableName() {
        return "STOCKED natural join PRODUCT natural join COLOR natural join CLOTH_SIZE";
    }

    @Override
    public Boolean insertOrUpdate(ProductColorSize product) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected ProductColorSize parseData(HashMap<String, Object> obj) {
        return new ProductColorSize(
                new Product((int) obj.get("idprod"), obj.get("nameprod").toString(), obj.get("descriptionprod").toString(), (float) obj.get("priceprod")),
                new Color(obj.get("namecolor").toString()),
                new ClothSize((int) obj.get("idsize"), obj.get("namesize").toString()), (int) obj.get("quantitystocked"));
    }

    @Override
    protected Boolean exist(ProductColorSize obj) {
        return obj.getColor() != null && obj.getColor().getName() != null
                && obj.getProduct() != null && obj.getProduct().getId() != -1
                && obj.getSize() != null && obj.getSize().getId() != -1;
    }

}
