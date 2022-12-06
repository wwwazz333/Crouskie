package crouskiebackoffice.model;

import java.util.List;

public class ProductAttachImage implements AttachImage {

    private Product product;

    public ProductAttachImage(Product product) {
        this.product = product;
    }

    @Override
    public void attachPicture(Picture pic) {
        pic.setIdProd(product.getId());
    }

    @Override
    public List<Picture> getPictures() {
        return product.getPictures();
    }

    @Override
    public boolean isSingleAttach() {
        return false;
    }

}
