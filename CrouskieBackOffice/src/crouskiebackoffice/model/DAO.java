package crouskiebackoffice.model;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class DAO<T> {

    public List<HashMap<String, Object>> selectAll(String request, Object[] args) throws SQLException {
        PreparedStatement pstmt = ConnectionDB.getInstance().getConnection().prepareStatement(request);

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
        }
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        final int columnCount = resultSet.getMetaData().getColumnCount();

        List<HashMap<String, Object>> results = new LinkedList<>();
        while (resultSet.next()) {
            HashMap<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i).toLowerCase(), resultSet.getObject(i));
            }
            results.add(row);
        }

        pstmt.close();
        return results;
    }

    public int execute(String request, Object[] args) throws SQLException {
        PreparedStatement pstmt = ConnectionDB.getInstance().getConnection().prepareStatement(request);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
        }
        int nbrEditedRow = pstmt.executeUpdate();
        pstmt.close();
        return nbrEditedRow;
    }

    public abstract Boolean insertOrUpdate(T product) throws SQLException;

    public List<T> getAllData() throws SQLException, NumberFormatException {
        List<HashMap<String, Object>> res = selectAll("SELECT * FROM " + getTableName(), null);
        List<T> datas = new LinkedList<>();

        for (HashMap<String, Object> values : res) {
            datas.add(parseData(values));
        }

        return datas;
    }

    protected abstract T parseData(HashMap<String, Object> obj);

    protected abstract String getTableName();

}
