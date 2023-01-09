package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.Picture;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Classe DAO permettant de manipuler des objets de type Picture dans la base de
 * données.
 *
 * Hérite de la classe abstraite DAO.
 */
public class DAOPicture extends DAO<Picture> {

    /**
     * Retourne le nom de la table dans laquelle les opérations sont effectuées.
     *
     * @return le nom de la table
     */
    @Override
    protected String getTableName() {
        return "picture";
    }

    /**
     * Insère ou met à jour un objet Picture dans la base de données.
     *
     * @param obj l'objet à insérer ou mettre à jour
     * @return true si l'opération a réussi, false sinon
     * @throws SQLException en cas d'erreur lors de l'exécution de la requête
     * @throws ErrorHandelabelAdapter en cas d'erreur lors du traitement de
     * l'objet
     */
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

    /**
     * Supprime un objet Picture de la base de données.
     *
     * @param obj l'objet à supprimer
     * @return true si l'opération a réussi, false sinon
     * @throws SQLException en cas d'erreur lors de la connexion SQL
     */
    @Override
    public Boolean remove(Picture obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getPath()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE pathpicture = ? ", args) == 1;
    }

    /**
     * Vérifie si un objet Picture existe dans la base de données.
     *
     * @param obj l'objet à vérifier
     * @return true si l'objet existe, false sinon
     * @throws SQLException en cas d'erreur lors de l'exécution de la requête
     * @throws ErrorHandelabelAdapter en cas d'erreur lors du traitement de
     * l'objet
     */
    @Override
    public Boolean exist(Picture obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getPath()};
        return ((long) super.selectAll("SELECT count(*) as nbr FROM " + getTableName() + " WHERE pathpicture = ?", args).get(0).get("nbr")) == 1;
    }

    /**
     * Transforme un enregistrement de base de données sous forme de
     * {@link HashMap} en un objet {@link Picture}.
     *
     * @param obj l'enregistrement sous forme de {@link HashMap}
     * @return l'objet {@link Picture} correspondant
     */
    @Override
    protected Picture parseData(HashMap<String, Object> obj) {
        return new Picture(obj.get("pathpicture").toString(), obj.get("altpicture").toString(), (int) obj.get("idprod"));
    }

}
