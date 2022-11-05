package crouskiebackoffice.model;


public class Collection {
    private int id;
    private String name;

    public Collection(int id, String name) {
        this.id = id;
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
        return "Collection{" + "id=" + id + ", name=" + name + '}';
    }
    
    
    
}
