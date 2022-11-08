package crouskiebackoffice.model;

import java.sql.SQLException;
import java.util.HashMap;

public class DAOClothSize extends DAO<ClothSize> {

    @Override
    public Boolean insertOrUpdate(ClothSize clothSize) throws SQLException {
        if (exist(clothSize)) {
            Object[] args = {clothSize.getName(), clothSize.getId()};
            return super.execute("UPDATE " + getTableName() + " SET namesize = ? WHERE idsize = ?", args) == 1;
        } else {
            Object[] args = {clothSize.getName()};
            return super.execute("INSERT INTO " + getTableName() + " (namesize) VALUES (?)", args) == 1;
        }
    }

    @Override
    public Boolean exist(ClothSize clothSize) throws SQLException {
        return clothSize.getId() != -1;
    }

    @Override
    protected ClothSize parseData(HashMap<String, Object> obj) {
        return new ClothSize((int) obj.get("idsize"), obj.get("namesize").toString());
    }

    @Override
    protected String getTableName() {
        return "CLOTH_SIZE";
    }

    @Override
    public Boolean remove(ClothSize obj) throws SQLException {
        Object[] args = {obj.getId()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE idsize = ? ", args) == 1;
    }
}
