package crouskiebackoffice.controle;

import crouskiebackoffice.model.DataProduct;
import crouskiebackoffice.model.ModelVisualisationProduct;
import crouskiebackoffice.model.Observer;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.view.EditProduct;
import java.util.LinkedList;

/**
 * Contrôleur pour afficher une table de produits dans une interface
 * utilisateur. S'inscrit comme observateur des données de produits pour être
 * informé des mises à jour des données. Utilise une classe de modèle de
 * visualisation de produits pour stocker et afficher les données de produits.
 */
public class ControllerProductTable implements Observer {

    // Modèle de visualisation de produits pour stocker et afficher les données de produits
    ModelVisualisationProduct modelVisualisationProduct;

    /**
     * Constructeur pour s'inscrire comme observateur des données de produits et
     * initialiser le modèle de visualisation de produits avec les données
     * actuelles de produits.
     */
    public ControllerProductTable() {
        try {
            ErrorHandeler.getInstance().exec(() -> {
                DataProduct.getInstance().registerObserver(this);
                // Initialiser le modèle de visualisation de produits avec les données actuelles de produits
                modelVisualisationProduct = new ModelVisualisationProduct(this, DataProduct.getInstance().getData());
                return true;
            });
        } catch (Exception e) {
        }

    }

    /**
     * Getter pour le modèle de visualisation de produits. Si le modèle est
     * null, crée un nouveau modèle vide.
     *
     * @return Le modèle de visualisation de produits
     */
    public ModelVisualisationProduct getModelVisualisationProduct() {
        if (modelVisualisationProduct == null) {
            return new ModelVisualisationProduct(this, new LinkedList<>());
        }
        return modelVisualisationProduct;
    }

    /**
     * Navigue vers une vue d'édition de produit avec le produit actuel en
     * argument.
     *
     * @param currentProduct Le produit à éditer
     */
    public void goToEdit(Product currentProduct) {
        Navigator.getInstance().goTo(new EditProduct(currentProduct), "editProduct");
    }

    /**
 * Méthode appelée lorsque les données de produits sont mises à jour.
 * Met à jour le modèle de visualisation de produits avec les données actuelles de produits.
 */
    @Override
    public void update() {
        ErrorHandeler.getInstance().exec(() -> {
            modelVisualisationProduct.setData(DataProduct.getInstance().getData());
            return true;
        });

    }

}
