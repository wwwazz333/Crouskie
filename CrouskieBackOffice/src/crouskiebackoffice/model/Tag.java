package crouskiebackoffice.model;

/**
 * Represente un tag dans la base de donnée.
 */
public class Tag implements HasName{

    private int id;
    private String name;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(String name) {
        this.id = -1;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
