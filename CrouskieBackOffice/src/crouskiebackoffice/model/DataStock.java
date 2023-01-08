package crouskiebackoffice.model;

import crouskiebackoffice.model.dao.DAOStock;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant le modèle de données pour le stock de produits.
 */
public class DataStock extends Notifier {

    private static DataStock instance;

    /**
     * DAO permettant de récupérer les données du stock depuis la base de
     * données.
     */
    DAOStock dao;
    /**
     * Liste des produits du stock.
     */
    List<ProductColorSize> data = new LinkedList<>();

    /**
     * Méthode permettant de récupérer l'instance unique de la classe DataStock.
     *
     * @return l'instance unique de la classe DataStock.
     * @throws Exception si une erreur est survenue lors de la création de
     * l'instance.
     */
    public static DataStock getInstance() throws Exception {
        if (instance == null) {
            instance = new DataStock();
        }
        return instance;
    }

    /**
     * Constructeur privé de la classe DataStock. Initialise le DAO et récupère
     * les données du stock depuis la base de données.
     *
     * @throws Exception si une erreur est survenue lors de la récupération des
     * données du stock depuis la base de données.
     */
    private DataStock() throws Exception {
        dao = new DAOStock();
        notif();
    }

    /**
     * Met à jour les données et notifie les observateurs.
     *
     * @throws Exception Si une erreur se produit lors de la mise à jour des
     * données.
     */
    @Override
    public void notif() throws Exception {
        data = dao.getAllData("nameprod");

        super.notif();
    }

    /**
     * Retourne les données de cette classe.
     *
     * @return Les données de cette classe.
     */
    public List<ProductColorSize> getData() {
        return data;
    }
}
