/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.Collection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author wwwazz
 */
public class DAOCollection extends DAO<Collection> {

    @Override
    protected String getTableName() {
        return "COLLECTION";
    }

    @Override
    public Boolean insertOrUpdate(Collection obj) throws SQLException, ErrorHandelabelAdapter {
        if (exist(obj)) {
            Object[] args = {obj.getName(), obj.getPathPicture(), obj.getId()};
            return super.execute("UPDATE " + getTableName() + " SET namecollection = ?, PATHPICTURE = ? WHERE idcollection = ?", args) == 1;
        } else {
            Object[] args = {obj.getName(), obj.getPathPicture()};
            return super.execute("INSERT INTO " + getTableName() + " ( namecollection , PATHPICTURE ) VALUES (?, ?)", args) == 1;
        }
    }

    @Override
    public Boolean remove(Collection obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getId()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE idcollection = ?", args) == 1;
    }

    @Override
    public Boolean exist(Collection obj) throws SQLException {
        return obj.getId() != -1;
    }

    @Override
    protected Collection parseData(HashMap<String, Object> obj) {
        return new Collection((int) obj.get("idcollection"), obj.get("namecollection").toString(), obj.get("pathpicture").toString());
    }

}
