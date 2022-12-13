package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.MultipleInsertSQL;
import crouskiebackoffice.model.Tag;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DAOTag extends DAO<Tag> implements MultipleInsertSQL<Tag> {

    @Override
    public Boolean insertOrUpdate(Tag obj) throws SQLException, ErrorHandelabelAdapter {
        if (exist(obj)) {
            Object[] args = {obj.getName(), obj.getId()};
            return super.execute("UPDATE TABLE " + getTableName() + " SET  nametag = ? WHERE idtag = ?", args) == 1;
        } else {
            Object[] args = {obj.getName()};
            return super.execute("INSERT INTO " + getTableName() + " (nametag) values (?)", args) == 1;
        }
    }

    @Override
    public Boolean insertAll(List<Tag> list) throws SQLException, ErrorHandelabelAdapter {
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
        return super.execute("INSERT INTO " + getTableName() + " (nametag) values (" + ptsInterogration.toString() + ")", args) == 1;
    }

    @Override
    public Boolean exist(Tag obj) throws SQLException {
        return obj.getId() != -1;
    }

    @Override
    protected Tag parseData(HashMap<String, Object> obj) {
        return new Tag((int) obj.get("idtag"), obj.get("nametag").toString());
    }

    @Override
    protected String getTableName() {
        return "tag";
    }

    @Override
    public Boolean remove(Tag obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getId()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE idtag = ? ", args) == 1;
    }
}
