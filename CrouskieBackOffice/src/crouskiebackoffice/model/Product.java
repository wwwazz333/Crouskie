package crouskiebackoffice.model;

public class Product {

    private final int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }
    private final String name;
    private final String description;
    private final float price;

    public Product(int id, String name, String description, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price;
    }

}
