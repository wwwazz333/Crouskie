package crouskiebackoffice.model;

import java.util.List;

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
     * @return true si il doit y avoir qu'une image attaché à l'interface graphique
     */
    @Override
    public boolean isSingleAttach() {
        return false;
    }
}
