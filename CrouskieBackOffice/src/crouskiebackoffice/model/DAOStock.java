package crouskiebackoffice.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DAOStock extends DAO<ProductColorSize> {

    @Override
    protected String getTableName() {
        return "STOCKED";
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
    public List<ProductColorSize> getAllData() throws SQLException, NumberFormatException {
        List<HashMap<String, Object>> res = selectAll("SELECT * FROM " + getTableName() + " natural join PRODUCT natural join COLOR natural join CLOTH_SIZE ORDER BY idprod", null);
        List<ProductColorSize> datas = new LinkedList<>();

        for (HashMap<String, Object> values : res) {
            datas.add(parseData(values));
        }

        return datas;
    }

}
