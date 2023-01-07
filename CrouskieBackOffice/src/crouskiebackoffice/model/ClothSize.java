package crouskiebackoffice.model;

/**
 * Cette classe représente une taille de vêtement. Elle implémente l'interface
 * {@link HasName} pour fournir un nom à la taille.
 *
 */
public class ClothSize implements HasName {

    /**
     * L'ID de la taille de vêtement.
     */
    private final int id;

    /**
     * Le nom de la taille de vêtement.
     */
    private final String name;

    /**
     * Crée une nouvelle instance de la classe {@code ClothSize} avec l'ID et le
     * nom spécifiés.
     *
     * @param id L'ID de la taille de vêtement.
     * @param name Le nom de la taille de vêtement.
     */
    public ClothSize(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Crée une nouvelle instance de la classe {@code ClothSize} avec le nom
     * spécifié. L'ID sera défini comme -1.
     *
     * @param name Le nom de la taille de vêtement.
     */
    public ClothSize(String name) {
        this.id = -1;
        this.name = name;
    }

    /**
     * Obtient l'ID de la taille de vêtement.
     *
     * @return L'ID de la taille de vêtement.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient le nom de la taille de vêtement.
     *
     * @return Le nom de la taille de vêtement.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de la
     * taille de vêtement. Cette méthode renvoie le nom de la taille de
     * vêtement.
     *
     * @return Le nom de la taille de vêtement.
     */
    @Override
    public String toString() {
        return name;
    }

}
