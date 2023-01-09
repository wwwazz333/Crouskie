package crouskiebackoffice.model;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface pour les DAO possèdent la fonctionnalité de faire plusieur insertion à la fois
 */
public interface MultipleInsertSQL<T> {

    /**
     * Inserer toutes les données dans la base de donnée
     * @param list Les donnée à inseré
     * @return Si l'insertion à été un succes ou non
     * @throws SQLException en cas d'erreur SQL
     * @throws ErrorHandelabelAdapter 
     */
    public Boolean insertAll(List<T> list) throws SQLException, ErrorHandelabelAdapter;
}
