package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.MultipleInsertSQL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DAOClothSize extends DAO<ClothSize> implements MultipleInsertSQL<ClothSize> {

    @Override
    public Boolean insertOrUpdate(ClothSize clothSize) throws SQLException, ErrorHandelabelAdapter {
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
    public Boolean remove(ClothSize obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getId()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE idsize = ? ", args) == 1;
    }

    @Override
    public Boolean insertAll(List<ClothSize> list) throws SQLException, ErrorHandelabelAdapter {
        if (list.size() <= 0) {
            return true;
        }
        StringBuilder ptsInterogration = new StringBuilder();

        Object[] args = new Object[list.size()];
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                ptsInterogration.append("?");
            } else {
                ptsInterogration.append(", ?");
            }

            args[i] = list.get(i).getName();
        }
        return super.execute("INSERT INTO " + getTableName() + " (namesize) values (" + ptsInterogration.toString() + ")", args) == 1;
    }
}
