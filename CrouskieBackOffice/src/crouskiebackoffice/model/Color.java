package crouskiebackoffice.model;

public class Color implements HasName{

    private final String name;

    public Color(String name) {
        this.name = name;
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
