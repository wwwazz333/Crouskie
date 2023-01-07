package crouskiebackoffice.controle;

import crouskiebackoffice.model.DataStock;
import crouskiebackoffice.model.ProductColorSize;
import crouskiebackoffice.view.StockTableView;
import java.util.LinkedList;
import java.util.List;

/**
 * Contrôleur pour afficher une table de stock de produits dans une interface
 * utilisateur. Utilise une vue de table de stock de produits pour afficher les
 * données de stock de produits.
 */
public class ControllerStockTable {

    // Vue de table de stock de produits pour afficher les données de stock de produits
    private StockTableView view;

    // Liste de données de stock de produits
    List<ProductColorSize> data = new LinkedList<>();

    /**
     * Constructeur pour initialiser la vue de table de stock de produits.
     *
     * @param view Vue de table de stock de produits
     */
    public ControllerStockTable(StockTableView view) {
        this.view = view;
    }

    /**
     * Obtient toutes les données de stock de produits à partir de DataStock et
     * les stocke dans la liste de données de stock de produits.
     *
     * @return La liste de données de stock de produits
     */
    public List<ProductColorSize> getAllData() {
        // Obtenir les données de stock de produits à partir de DataStock et les stocker dans la liste de données de stock de produits
        ErrorHandeler.getInstance().exec(() -> {
            setData(DataStock.getInstance().getData());
            return true;
        });

        return data;
    }

    /**
     * Setter pour la liste de données de stock de produits.
     *
     * @param data Liste de données de stock de produits
     */
    public void setData(List<ProductColorSize> data) {
        this.data = data;
    }
}
