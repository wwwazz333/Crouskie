package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.Collection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Classe permettant de gérer les opérations CRUD sur les objets de type
 * Collection dans la base de données.
 *
 * Hérite de la classe abstraite {@link DAO}.
 */
public class DAOCollection extends DAO<Collection> {

    /**
     * Retourne le nom de la table dans la base de données correspondant aux
     * objets de type Collection.
     *
     * @return le nom de la table dans la base de données correspondant aux
     * objets de type Collection.
     */
    @Override
    protected String getTableName() {
        return "collection";
    }

    /**
     * Insère ou met à jour un objet de type Collection dans la base de données.
     *
     * @param obj l'objet de type Collection à insérer ou mettre à jour.
     * @return true si l'opération a réussi, false sinon.
     * @throws SQLException si une erreur de base de données survient.
     * @throws ErrorHandelabelAdapter si une erreur inattendue survient.
     */
    @Override
    public Boolean insertOrUpdate(Collection obj) throws SQLException, ErrorHandelabelAdapter {
        if (exist(obj)) {
            Object[] args = {obj.getName(), obj.getPathPicture(), obj.getId()};
            return super.execute("UPDATE " + getTableName() + " SET namecollection = ?, pathpicture"
                    + " = ? WHERE idcollection = ?", args) == 1;
        } else {
            Object[] args = {obj.getName(), obj.getPathPicture()};
            return super.execute("INSERT INTO " + getTableName() + " ( namecollection , pathpicture ) VALUES (?, ?)", args) == 1;
        }
    }

    /**
     * Supprime l'objet Collection de la base de données
     *
     * @param obj l'objet Collection à supprimer
     * @return true si la suppression a été effectuée, false sinon
     * @throws SQLException une exception peut être levée en cas de problème
     * avec la requête SQL
     * @throws ErrorHandelabelAdapter une exception peut être levée en cas de
     * problème d'intégrité des données
     */
    @Override
    public Boolean remove(Collection obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getId()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE idcollection = ?", args) == 1;
    }

    /**
     * Vérifie si l'objet passé en paramètre existe déjà dans la base de
     * données.
     *
     * @param obj l'objet à vérifier
     * @return true si l'objet existe déjà dans la base de données, false sinon
     * @throws SQLException une exception peut être levée en cas d'erreur lors
     * de l'exécution de la requête SQL
     */
    @Override
    public Boolean exist(Collection obj) throws SQLException {
        return obj != null && obj.getId() != -1;
    }

    /**
     * Convertit un enregistrement de la base de données sous la forme d'un
     * objet de type `Collection`.
     *
     * @param obj les données à convertir sous la forme d'un HashMap
     * @return l'objet de type `Collection` créé à partir des données
     */
    @Override
    protected Collection parseData(HashMap<String, Object> obj) {
        return new Collection((int) obj.get("idcollection"), obj.get("namecollection").toString(), obj.get("pathpicture").toString());
    }

}
