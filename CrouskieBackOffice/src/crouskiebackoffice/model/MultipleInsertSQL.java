package crouskiebackoffice.model;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wwwazz
 */
public interface MultipleInsertSQL<T> {

    public Boolean insertAll(List<T> list) throws SQLException, ErrorHandelabelAdapter;
}
