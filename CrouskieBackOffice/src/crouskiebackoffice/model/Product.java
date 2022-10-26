package crouskiebackoffice.model;

public class Product {

    private int id;

    private String name;
    private String description;
    private float price;

    public Product(int id, String name, String description, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, float price) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Boolean isLinkedToDB() {
        return id != -1;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price;
    }

}
