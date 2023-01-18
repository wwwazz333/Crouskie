package crouskiebackoffice.model;

import java.util.List;

/**
 * Interface utilisée pour attacher des images à un objet.
 */
public interface AttachImage {
    
    /**
     * Attache une image à l'objet.
     * @param pic L'image à attacher
     */
    public void attachPicture(Picture pic);
    
    /**
     * Récupère la liste des images attachées à l'objet.
     * @return La liste des images attachées à l'objet
     */
    public List<Picture> getPictures();
    
    /**
     * 
     * @return l'id du produit auquelle attache cette objet
     */
    public int getProductId();
    
    /**
     * Indique si l'objet ne peut avoir qu'une seule image attachée à la fois.
     * @return true si l'objet ne peut avoir qu'une seule image attachée à la fois, false sinon
     */
    public boolean isSingleAttach();
}
