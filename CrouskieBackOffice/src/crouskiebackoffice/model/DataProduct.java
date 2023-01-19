package crouskiebackoffice.model;

import crouskiebackoffice.model.dao.DAOProduct;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe permettant de stocker et de gérer les données des produits. Implémente
 * l'interface Notifier pour permettre la notification des vues lors de
 * modifications de ces données.
 */
public class DataProduct extends Notifier {

    /**
     * Instance unique de la classe (Design pattern Singleton).
     */
    private static DataProduct instance;

    /**
     * DAO permettant l'accès aux données des produits dans la base de données.
     */
    DAOProduct daoProduct;
    /**
     * Liste des produits stockés.
     */
    List<Product> data = new LinkedList<>();

    /**
     * Retourne l'instance unique de la classe (Design pattern Singleton).
     *
     * @return instance de DataProduct
     * @throws Exception
     */
    public static DataProduct getInstance() throws Exception {
        if (instance == null) {
            instance = new DataProduct();
        }
        return instance;
    }

    /**
     * Constructeur privé de la classe (Design pattern Singleton). Initialise le
     * DAO et notifie les vues lors de l'instanciation.
     *
     * @throws Exception
     */
    private DataProduct() throws Exception {
        daoProduct = new DAOProduct();
        notif();
    }

    /**
     * Redéfinition de la méthode notif() de l'interface Notifier. Met à jour
     * les données des produits et notifie les vues lors de modifications.
     *
     * @throws Exception
     */
    @Override
    public void notif() throws Exception {
        data = daoProduct.getAllData("envente DESC, nameprod");

        super.notif();
    }

    /**
     * Retourne la liste des produits stockés.
     *
     * @return liste des produits
     */
    public List<Product> getData() {
        return data;
    }

    /**
     * Retourne la liste des produits stockés.
     *
     * @param outSale true si les produits retourner doivent comprendre ceux qui
     * sont hors vente
     * @return liste des produits
     */
    public List<Product> getData(boolean outSale) {
        if (outSale) {
            return data;
        }
        List<Product> d = new LinkedList<>();
        for (Product p : data) {
            if (p.isEnVente()) {
                d.add(p);
            }
        }
        return d;
    }
}
