package crouskiebackoffice.model;

public class Color {

    private String name;

    public Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    

    @Override
    public String toString() {
        return name;
    }

}
