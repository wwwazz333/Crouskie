package crouskiebackoffice.exceptions;

public class ErrorDownloadImage extends Exception {

    public ErrorDownloadImage(String fileName) {
        super("Impossible de télécharger le fichier : " + fileName);
    }

}
