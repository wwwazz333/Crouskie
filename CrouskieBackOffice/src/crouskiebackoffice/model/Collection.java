package crouskiebackoffice.model;

/**
 * Classe modélisant une collection de vêtements.
 */
public class Collection {

    /**
     * L'ID de la collection dans la base de données. Valeur par défaut: -1
     */
    private int id = -1;
    /**
     * Le nom de la collection.
     */
    private String name;
    /**
     * Le chemin vers l'image de la collection.
     */
    private String pathPicture;

    /**
     * Crée une nouvelle instance de Collection en copiant les valeurs de
     * l'objet other.
     *
     * @param other l'objet à copier
     */
    public Collection(Collection other) {
        if (other != null) {
            this.id = other.id;
            this.name = other.name;
            this.pathPicture = other.pathPicture;
        }
    }

    /**
     * Crée une nouvelle instance de Collection avec l'ID et le nom spécifiés.
     *
     * @param id l'ID de la collection
     * @param name le nom de la collection
     */
    public Collection(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructeur.
     *
     * @param id l'ID de la collection
     * @param name le nom de la collection
     * @param pathPicture le chemin vers l'image de la collection
     */
    public Collection(int id, String name, String pathPicture) {
        this.id = id;
        this.name = name;
        this.pathPicture = pathPicture;
    }

    /**
     * Retourne le chemin vers l'image de la collection.
     *
     * @return Le chemin vers l'image de la collection.
     */
    public String getPathPicture() {
        return pathPicture;
    }

    /**
     * Retourne l'ID de la collection.
     *
     * @return L'ID de la collection.
     */
    public int getId() {
        return id;
    }

    /**
     * Modifie l'ID de la collection.
     *
     * @param id Le nouvel ID de la collection.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la collection.
     *
     * @return Le nom de la collection.
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de la collection.
     *
     * @param name Le nouveau nom de la collection.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modifie le chemin vers l'image de la collection.
     *
     * @param pathPicture Le nouveau chemin vers l'image de la collection.
     */
    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }

    @Override
    public String toString() {
        return name;
    }
}
