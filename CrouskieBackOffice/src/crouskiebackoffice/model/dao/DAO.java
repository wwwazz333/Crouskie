package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.ConnectionDB;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class DAO<T> {

    /**
     * Converti un ResultSet en une liste de HashMap
     *
     * @param resultSet le ResultSet à convertir
     * @return une liste de HashMap
     *
     * @throws SQLException une exception peut être levée suite à la requête
     */
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
     * Récupère une liste de lignes retournées par la requête qui a pour
     * arguments args
     *
     * @param request la requête SQL à envoyer
     * @param args les arguments qui remplacent "?"
     * @return une liste de lignes retournées par la requête
     *
     * @throws SQLException une exception peut être levée suite à la requête
     */
    protected List<HashMap<String, Object>> selectAll(String request, Object[] args) throws SQLException, ErrorHandelabelAdapter {
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
     * Envoie une requête de modification de données à la base de données.
     *
     * @param request la requête à envoyer
     * @param args les arguments de la requête
     * @return le nombre de lignes modifiées par la requête
     * @throws SQLException une Exception peut se produire en raison de la
     * requête
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     *
     */
    protected int execute(String request, Object[] args) throws SQLException, ErrorHandelabelAdapter {
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
     * Insère une nouvelle ligne dans la base de données avec les valeurs
     * spécifiées dans la requête et les arguments.
     *
     * @param request la requête à envoyer
     * @param idToReturn le nom de la colonne à retourner
     * @param args les arguments de la requête
     * @return la clé générée ou null si l'insertion a échoué
     * @throws SQLException une exception peut survenir en raison de la requête
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     */
    public List<HashMap<String, Object>> insert(String request, String idToReturn, Object[] args) throws SQLException, ErrorHandelabelAdapter {
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
        if (nbrEditedRow == 0) {
            return null;
        }
        return generatedKeys;
    }

    /**
     * Insère ou met à jour l'objet spécifié dans la base de données.
     *
     * @param obj l'objet à insérer ou mettre à jour
     * @param forceInsertWheneSave forcé l'insertion
     * @return true si l'opération a réussi, false sinon
     * @throws SQLException une exception peut être levée en cas de problème
     * avec la requête SQL
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     */
    public abstract Boolean insertOrUpdate(T obj, boolean forceInsertWheneSave) throws SQLException, ErrorHandelabelAdapter;

    /**
     * Cette méthode permet de récupérer toutes les données de la table
     * concernée sous forme de liste d'objets.
     *
     * @return Une liste d'objets représentant toutes les lignes de la table.
     * @throws SQLException Une exception peut être levée en cas de problème
     * avec la requête SQL.
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     *
     */
    public List<T> getAllData() throws SQLException, ErrorHandelabelAdapter {
        return getAllData(null);
    }

    /**
     * Cette méthode permet de récupérer toutes les données de la table
     * concernée sous forme de liste d'objets.
     *
     * @param orderby ordonné les donnée par la colone {@code orderby}
     *
     * @return Une liste d'objets représentant toutes les lignes de la table.
     * @throws SQLException Une exception peut être levée en cas de problème
     * avec la requête SQL.
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     *
     */
    public final List<T> getAllData(String orderby) throws SQLException, ErrorHandelabelAdapter {
        List<HashMap<String, Object>> res = selectAll(getRequestForAllData() + ((orderby != null) ? " ORDER BY " + orderby : ""), null);
        List<T> datas = new LinkedList<>();

        for (HashMap<String, Object> values : res) {
            datas.add(parseData(values));
        }

        return datas;
    }

    /**
     * Supprime tous les enregistrements de la table correspondante à la classe
     * générique T qui satisfont les conditions spécifiées par la clause WHERE.
     *
     * @param whereClause la clause WHERE qui spécifie les conditions pour
     * lesquelles les enregistrements doivent être supprimés
     * @param whereArgs les arguments qui remplacent les "?" dans la clause
     * WHERE
     * @return le nombre de lignes supprimées
     * @throws SQLException une exception peut survenir en raison de la requête
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     *
     */
    public Boolean romoveWhere(String whereClause, Object[] whereArgs) throws SQLException, ErrorHandelabelAdapter {
        return execute("DELTE FROM " + getTableName() + " WHERE " + whereClause, whereArgs) == 0;
    }

    /**
     *
     * @return la requette pour récuper toutes les donnée de la table (ou lié à
     * la table).
     */
    protected String getRequestForAllData() {
        return "SELECT * FROM " + getTableName();
    }

    /**
     * Débuté la transaction
     *
     * @throws SQLException
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     *
     */
    public void startTransaction() throws SQLException, ErrorHandelabelAdapter {
        execute("SET autocommit = 0", null);
        execute("START TRANSACTION", null);
    }

    /**
     * Finir la transaction
     *
     * @throws SQLException
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     *
     */
    public void endTransaction() throws SQLException, ErrorHandelabelAdapter {
        execute("SET autocommit = 1", null);
        execute("COMMIT", null);
    }

    /**
     * placé un savepoint
     *
     * @param name le nom du savepoint
     * @throws SQLException
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     *
     */
    public void setSavePoint(String name) throws SQLException, ErrorHandelabelAdapter {
        if (name != null) {
            execute("SAVEPOINT " + name, null);
        }
    }

    /**
     * retourner au savepoint {@code name}
     *
     * @param name le savepoint auquelle retourné
     * @throws SQLException
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     */
    public void rollbackTo(String name) throws SQLException, ErrorHandelabelAdapter {
        if (name != null) {
            execute("ROLLBACK TO SAVEPOINT " + name, null);
        }
    }

    /**
     * Supprimer un élément de la BD
     *
     * @param obj l'élément
     * @return si la suprressions est un succès
     * @throws SQLException
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     */
    public abstract Boolean remove(T obj) throws SQLException, ErrorHandelabelAdapter;

    /**
     * Supprimer l'élément obj de la BD existe
     *
     * @param obj l'élément
     * @return si l'élément existe
     * @throws SQLException
     * @throws ErrorHandelabelAdapter une exception peut survenir en raison
     * d'une erreur de connection SQL
     */
    public abstract Boolean exist(T obj) throws SQLException, ErrorHandelabelAdapter;

    /**
     * Transformer les données récuperer de la requet en un Objet representant la BD
     * @param obj les données récuperer de la requete.
     * @return L'objet créé à partir des données.
     */
    protected abstract T parseData(HashMap<String, Object> obj);

    /**
     *
     * @return le nom de la table.
     */
    protected abstract String getTableName();
}
