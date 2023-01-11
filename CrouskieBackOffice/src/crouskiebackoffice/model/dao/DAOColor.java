package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.MultipleInsertSQL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Classe qui gère la persistance en base de données pour la classe
 * {@link Color}.
 */
public class DAOColor extends DAO<Color> implements MultipleInsertSQL<Color> {

    /**
     * Insère ou met à jour un objet {@link Color} en base de données. Il ne
     * peut pas être mis à jour car la clef est le seul paramètre qui est le nom
     * de la couleur.
     *
     * @param color la couleur à insérer
     * @return true si la couleur est insérée
     * @throws SQLException une Exception peut arriver en raison de la requête
     */
    @Override
    public Boolean insertOrUpdate(Color color) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {color.getName(), color.getCode()};
        return super.execute("INSERT INTO " + getTableName() + " (namecolor, code) VALUES (?, ?)", args) == 1;

    }

    /**
     * Vérifie si un objet {@link Color} existe en base de données.
     *
     * @param obj l'objet {@link Color} à vérifier
     * @return true si l'objet existe en base de données
     * @throws SQLException une Exception peut arriver en raison de la requête
     * @throws ErrorHandelabelAdapter
     */
    @Override
    public Boolean exist(Color obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getName()};
        return ((long) super.selectAll("SELECT count(*) as nbr FROM " + getTableName() + " WHERE namecolor = ?", args).get(0).get("nbr")) == 1;
    }

    /**
     * Transforme un enregistrement de base de données sous forme de
     * {@link HashMap} en un objet {@link Color}.
     *
     * @param obj l'enregistrement sous forme de {@link HashMap}
     * @return l'objet {@link Color} correspondant
     */
    @Override
    protected Color parseData(HashMap<String, Object> obj) {
        return new Color(obj.get("namecolor").toString());
    }

    /**
     * Retourne le nom de la table en base de données associée à la classe
     * {@link Color}.
     *
     * @return le nom de la table en base de données
     */
    @Override
    protected String getTableName() {
        return "color";
    }

    /**
     * Supprime un objet {@link Color} de la base de données.
     *
     * @param obj l'objet {@link Color} à supprimer
     * @return true si l'objet a été supprimé
     * @throws SQLException une Exception peut arriver en raison d'une erreur
     * connexion SQL
     */
    @Override
    public Boolean remove(Color obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getName()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE namecolor = ? ", args) == 1;
    }

    /**
     * @param list a list of Color objects to insert into the database
     * @return true if all the objects have been inserted into the database,
     * false otherwise
     * @throws SQLException an Exception may happen due to the request
     * @throws ErrorHandelabelAdapter an Exception may happen due to the request
     */
    @Override
    public Boolean insertAll(List<Color> list) throws SQLException, ErrorHandelabelAdapter {
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
        return super.execute("INSERT INTO " + getTableName() + " (namecolor) values (" + ptsInterogration.toString() + ")", args) == 1;
    }

}
