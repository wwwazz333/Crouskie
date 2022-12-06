package crouskiebackoffice.model;

public class Collection {

    private int id = -1;
    private String name;
    private String pathPicture;

    public Collection(Collection other) {
        if (other != null) {
            this.id = other.id;
            this.name = other.name;
            this.pathPicture = other.pathPicture;
        }
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }

    @Override
    public String toString() {
        return name;
    }
}
