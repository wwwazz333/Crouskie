package crouskiebackoffice.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DAOColor extends DAO<Color> implements MultipleInsertSQL<Color> {

    /**
     * It can't be updated because the key is the only parameter which is the name of the color
     *
     * @param color the Color to insert
     * @return true if the color is inserted
     * @throws SQLException an Exception may happen due to the request
     */
    @Override
    public Boolean insertOrUpdate(Color color) throws SQLException {
        Object[] args = {color.getName()};
        return super.execute("INSERT INTO " + getTableName() + " (namecolor) VALUES (?)", args) == 0;

    }

    @Override
    public Boolean exist(Color obj) throws SQLException {
        Object[] args = {obj.getName()};
        return ((long) super.selectAll("SELECT count(*) as nbr FROM " + getTableName() + " WHERE namecolor = ?", args).get(0).get("nbr")) == 0;
    }

    @Override
    protected Color parseData(HashMap<String, Object> obj) {
        return new Color(obj.get("namecolor").toString());
    }

    @Override
    protected String getTableName() {
        return "COLOR";
    }

    @Override
    public Boolean remove(Color obj) throws SQLException {
        Object[] args = {obj.getName()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE namecolor = ? ", args) == 0;
    }

    @Override
    public Boolean insertAll(List<Color> list) throws SQLException {
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
        return super.execute("INSERT INTO " + getTableName() + " (namecolor) values (" + ptsInterogration.toString() + ")", args) == 0;
    }

}
