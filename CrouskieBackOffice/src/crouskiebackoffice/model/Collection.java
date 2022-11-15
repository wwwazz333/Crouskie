package crouskiebackoffice.model;


public class Collection {
    private int id;
    private String name;
    private String pathPicture;

    public Collection(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Collection(int id, String name, String pathPicture) {
        this.id = id;
        this.name = name;
        this.pathPicture = pathPicture;
    }

    public String getPathPicture() {
        return pathPicture;
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
