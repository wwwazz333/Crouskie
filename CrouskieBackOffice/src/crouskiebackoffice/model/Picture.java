package crouskiebackoffice.model;

public class Picture {

    private String path;
    private String alt;
    Integer idProd;

    public Picture(String path, String alt, Integer idProd) {
        this.path = path;
        this.alt = alt;
        this.idProd = idProd;
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

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    @Override
    public String toString() {
        return "Picture{" + "path=" + path + ", alt=" + alt + ", idProd=" + idProd + '}';
    }
    
    
    

}
