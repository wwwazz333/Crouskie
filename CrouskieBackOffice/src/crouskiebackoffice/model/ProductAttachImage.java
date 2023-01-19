package crouskiebackoffice.model;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.dao.DAOProduct;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utiliser pour attacher une image, à une interface graphique
 */
public class ProductAttachImage implements AttachImage {

    private Product product;

    public ProductAttachImage(Product product) {
        this.product = product;
    }

    /**
     * Attacher une image
     *
     * @param pic l'image à attacher à l'interface graphique
     */
    @Override
    public void attachPicture(Picture pic) {
        pic.setIdProd(product.getId());
    }

    @Override
    public List<Picture> getPictures() {
        return product.getPictures();
    }

    /**
     *
     * @return true si il doit y avoir qu'une image attaché à l'interface
     * graphique
     */
    @Override
    public boolean isSingleAttach() {
        return false;
    }

    @Override
    public int getProductId() {
        if (product.getId() == -1) {
            try {
                product.setId(new DAOProduct().getNextId());
            } catch (SQLException | ErrorHandelabelAdapter ex) {
                Logger.getLogger(ProductAttachImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.err.println("idddd " + product.getId());
        return product.getId();
    }
}
