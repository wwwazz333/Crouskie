package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.Picture;
import java.sql.SQLException;
import java.util.HashMap;

public class DAOPicture extends DAO<Picture> {

    @Override
    protected String getTableName() {
        return "PICTURE";
    }

    @Override
    public Boolean insertOrUpdate(Picture obj) throws SQLException, ErrorHandelabelAdapter {
        if (exist(obj)) {
            Object[] args = {obj.getAlt(), obj.getPath()};
            return super.execute("UPDATE TABLE " + getTableName() + " SET  altpicture = ? WHERE pathpicture = ?", args) == 1;
        } else {
            Object[] args = {obj.getPath(), obj.getAlt(), obj.getIdProd()};
            return super.execute("INSERT INTO " + getTableName() + " (pathpicture, altpicture, idprod) VALUES (?, ?, ?)", args) == 1;
        }
    }

    @Override
    public Boolean remove(Picture obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getPath()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE pathpicture = ? ", args) == 1;
    }

    @Override
    public Boolean exist(Picture obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getPath()};
        return ((long) super.selectAll("SELECT count(*) as nbr FROM " + getTableName() + " WHERE pathpicture = ?", args).get(0).get("nbr")) == 1;
    }

    @Override
    protected Picture parseData(HashMap<String, Object> obj) {
        return new Picture(obj.get("pathpicture").toString(), obj.get("altpicture").toString(), (int) obj.get("idprod"));
    }

}
