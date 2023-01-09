package crouskiebackoffice.model;

/**
 * Représente une image avec un chemin d'accès, une légende et un identifiant de
 * produit associé.
 */
public class Picture {

    /**
     * Le chemin d'accès de l'image.
     */
    private String path;
    /**
     * La légende de l'image.
     */
    private String alt;
    /**
     * L'identifiant du produit associé à l'image.
     */
    Integer idProd;

    /**
     * Crée une nouvelle instance de {@link Picture} avec le chemin d'accès, la
     * légende et l'identifiant de produit spécifiés.
     *
     * @param path le chemin d'accès de l'image
     * @param alt la légende de l'image
     * @param idProd l'identifiant du produit associé à l'image
     */
    public Picture(String path, String alt, Integer idProd) {
        this.path = path;
        this.alt = alt;
        this.idProd = idProd;
    }

    /**
     *
     * Renvoie le chemin d'accès de l'image.
     *
     * @return le chemin d'accès de l'image
     */
    public String getPath() {
        return path;
    }

    /**
     * Renvoie la légende de l'image.
     *
     * @return la légende de l'image
     */
    public String getAlt() {
        return alt;
    }

    /**
     *
     * Définit la légende de l'image.
     *
     * @param alt la nouvelle légende de l'image
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    @Override
    public String toString() {
        return "Picture{" + "path=" + path + ", alt=" + alt + ", idProd=" + idProd + '}';
    }
}
