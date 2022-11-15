package crouskiebackoffice.model;

import java.util.List;

public class Product implements HasName{

    private int id;

    private String name;
    private String description;
    private float price;

    private List<Color> existingColor;
    private List<ClothSize> existingSize;
    private List<Tag> tags;
    private Collection collection;

    public void setExistingColor(List<Color> existingColor) {
        this.existingColor = existingColor;
    }

    public void setExistingSize(List<ClothSize> existingSize) {
        this.existingSize = existingSize;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Product(int id, String name, String description, float price, Collection collection) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.collection = collection;
    }

    public Product(String name, String description, float price, Collection collection) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.price = price;
        this.collection = collection;
    }

    public Product(String name, String description, float price, Collection collection, List<Color> existingColor, List<ClothSize> existingSize, List<Tag> tags) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.price = price;
        this.collection = collection;
        this.existingColor = existingColor;
        this.existingSize = existingSize;
        this.tags = tags;
    }

    public Product(int id, String name, String description, float price, Collection collection, List<Color> existingColor, List<ClothSize> existingSize, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.collection = collection;
        this.existingColor = existingColor;
        this.existingSize = existingSize;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
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

    public List<Color> getExistingColor() {
        return existingColor;
    }

    public List<ClothSize> getExistingSize() {
        return existingSize;
    }

    public List<Tag> getTags() {
        return tags;
    }

    
    public Collection getCollection() {
        return collection;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", existingColor=" + existingColor + ", existingSize=" + existingSize + ", tags=" + tags + ", collection=" + collection + '}';
    }

   

}
