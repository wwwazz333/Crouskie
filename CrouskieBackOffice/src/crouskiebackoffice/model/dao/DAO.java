package crouskiebackoffice.model.dao;

import crouskiebackoffice.model.ConnectionDB;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class DAO<T> {

    private List<HashMap<String, Object>> restultSet2HashMap(ResultSet resultSet) throws SQLException {
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

        return results;
    }

    /**
     * get a list of rows returned by the request that has for arguments args
     *
     * @param request the SQL request to send
     * @param args arguments that replace "?"
     * @return A list of rows returned by the request
     * @throws SQLException an Exception may happen due to the request
     */
    protected List<HashMap<String, Object>> selectAll(String request, Object[] args) throws SQLException {
        PreparedStatement pstmt = ConnectionDB.getInstance().getConnection().prepareStatement(request);

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
        }
        ResultSet resultSet = pstmt.executeQuery();

        List<HashMap<String, Object>> results = restultSet2HashMap(resultSet);

        resultSet.close();

        pstmt.close();
        return results;
    }

    /**
     *
     * @param request the request to send
     * @param args the arguments of the request
     * @return the numbers of edited row
     * @throws SQLException an Exception may happen due to the request
     */
    protected int execute(String request, Object[] args) throws SQLException {
        PreparedStatement pstmt = ConnectionDB.getInstance().getConnection().prepareStatement(request);
        System.out.println(request);
        System.out.println(Arrays.toString(args));
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
        }
        int nbrEditedRow = pstmt.executeUpdate();
        pstmt.close();
        return nbrEditedRow;
    }

    /**
     *
     * @param request the request to send
     * @param idToReturn the name of the column to return
     * @param args the arguments of the request
     * @return the generated key or null if the insert crached
     * @throws SQLException an Exception may happen due to the request
     */
    public List<HashMap<String, Object>> insert(String request, String idToReturn, Object[] args) throws SQLException {
        PreparedStatement pstmt = ConnectionDB.getInstance().getConnection().prepareStatement(request, new String[]{idToReturn});
        System.out.println(request);
        System.out.println(Arrays.toString(args));
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
        }
        int nbrEditedRow = pstmt.executeUpdate();
         var generatedKeys = restultSet2HashMap(pstmt.getGeneratedKeys());
        pstmt.close();
        if(nbrEditedRow == 0){
            return null;
        }
        return generatedKeys;
    }

    public abstract Boolean insertOrUpdate(T obj) throws SQLException;

    /**
     *
     * @return All the data of the concerned table as a List
     * @throws SQLException an Exception may happen due to the request
     */
    public List<T> getAllData() throws SQLException {
        return getAllData(null);
    }

    /**
     *
     * @param orderby ordered by it (ex : "SELECT * FROM ... ORDER BY
     * [orderby]")
     * @return All the data of the concerned table as a List
     * @throws SQLException an Exception may happen due to the request
     */
    public final List<T> getAllData(String orderby) throws SQLException {
        List<HashMap<String, Object>> res = selectAll(getRequestForAllData() + ((orderby != null) ? " ORDER BY " + orderby : ""), null);
        List<T> datas = new LinkedList<>();

        for (HashMap<String, Object> values : res) {
            datas.add(parseData(values));
        }

        return datas;
    }

    public Boolean romoveWhere(String condition, Object[] args) throws SQLException {
        return execute("DELTE FROM " + getTableName() + " WHERE " + condition, args) == 0;
    }

    protected String getRequestForAllData() {
        return "SELECT * FROM " + getTableName();
    }

    public void startTransaction() throws SQLException {
        execute("SET autocommit = 0", null);
        execute("START TRANSACTION", null);
    }

    public void endTransaction() throws SQLException {
        execute("SET autocommit = 1", null);
        execute("COMMIT", null);
    }

    public void setSavePoint(String name) throws SQLException {
        if (name != null) {
            execute("SAVEPOINT " + name, null);
        }
    }

    public void rollbackTo(String name) throws SQLException {
        if (name != null) {
            execute("ROLLBACK TO SAVEPOINT " + name, null);
        }
    }

    public abstract Boolean remove(T obj) throws SQLException;

    public abstract Boolean exist(T obj) throws SQLException;

    protected abstract T parseData(HashMap<String, Object> obj);

    protected abstract String getTableName();

}
