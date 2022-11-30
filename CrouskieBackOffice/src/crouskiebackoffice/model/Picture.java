package crouskiebackoffice.model;

public class Picture {

    private String path;
    private String alt;

    public Picture(String path, String alt) {
        this.path = path;
        this.alt = alt;
    }

    public String getPath() {
        return path;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

}
