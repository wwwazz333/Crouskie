package crouskiebackoffice.model;

public class ClothSize {

    private int id;
    private String name;

    public ClothSize(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClothSize(String name) {
        this.id = -1;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
