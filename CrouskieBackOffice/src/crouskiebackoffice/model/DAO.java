package crouskiebackoffice.model;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class DAO {

    public List<Object[]> selectAll(String request, Object[] args) throws SQLException {
        PreparedStatement pstmt = ConnectionDB.getInstance().getConnection().prepareStatement(request);
        List<Object[]> results = new LinkedList<>();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
        }
        ResultSet resultSet = pstmt.executeQuery();
        final int columnCount = resultSet.getMetaData().getColumnCount();

        while (resultSet.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 0; i < row.length; i++) {
                row[i] = resultSet.getObject(i+1);
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

}
