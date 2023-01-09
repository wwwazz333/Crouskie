package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.MultipleInsertSQL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Classe permettant la gestion en base de données de l'objet ClothSize. Elle
 * hérite de la classe abstraite DAO.
 */
public class DAOClothSize extends DAO<ClothSize> implements MultipleInsertSQL<ClothSize> {

    /**
     * Méthode permettant l'insertion ou la mise à jour d'un objet ClothSize en
     * base de données.
     *
     * @param clothSize L'objet ClothSize à insérer ou mettre à jour en base de
     * données.
     * @return true si l'insertion ou la mise à jour s'est effectuée avec
     * succès, false sinon.
     * @throws SQLException une exception peut être levée en cas d'erreur lors
     * de la requête SQL.
     * @throws ErrorHandelabelAdapter une exception peut être levée en cas
     * d'erreur lors de la requête SQL.
     */
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

    /**
     * Méthode permettant de vérifier l'existence d'un objet ClothSize en base
     * de données.
     *
     * @param clothSize L'objet ClothSize à vérifier en base de données.
     * @return true si l'objet existe en base de données, false sinon.
     * @throws SQLException une exception peut être levée en cas d'erreur lors
     * de la requête SQL.
     */
    @Override
    public Boolean exist(ClothSize clothSize) throws SQLException {
        return clothSize.getId() != -1;
    }

    /**
     * Parse un objet HashMap en un objet de la classe ClothSize
     *
     * @param obj un objet sous forme de HashMap
     * @return un objet ClothSize
     */
    @Override
    protected ClothSize parseData(HashMap<String, Object> obj) {
        return new ClothSize((int) obj.get("idsize"), obj.get("namesize").toString());
    }

    /**
     * Récupère le nom de la table à laquelle est liée la classe ClothSize
     *
     * @return le nom de la table
     */
    @Override
    protected String getTableName() {
        return "cloth_size";
    }

    /**
     * Supprime un objet ClothSize de la base de données
     *
     * @param obj l'objet à supprimer
     * @return true si la suppression a réussi, false sinon
     * @throws SQLException en cas d'erreur lors de l'exécution de la requête
     * @throws ErrorHandelabelAdapter en cas d'erreur lors de l'exécution de la
     * requête
     */
    @Override
    public Boolean remove(ClothSize obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getId()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE idsize = ? ", args) == 1;
    }

    /**

    Insère tous les éléments de la liste en base de données.
    @param list la liste d'éléments à insérer
    @return true si tous les éléments ont été correctement insérés, false sinon
    @throws SQLException une exception peut être levée en cas d'erreur lors de l'exécution de la requête SQL
    @throws ErrorHandelabelAdapter une exception peut être levée en cas d'erreur lors de la connection SQL
    */
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
