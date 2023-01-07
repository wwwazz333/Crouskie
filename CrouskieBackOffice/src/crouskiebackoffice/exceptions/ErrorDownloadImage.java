package crouskiebackoffice.exceptions;

/**
 * Exception levée lorsqu'il est impossible de télécharger une image.
 */
public class ErrorDownloadImage extends Exception {

    /**
     * Crée une nouvelle instance de ErrorDownloadImage.
     * @param fileName Le nom du fichier qui n'a pas pu être téléchargé
     */
    public ErrorDownloadImage(String fileName) {
        super("Impossible de télécharger le fichier : " + fileName);
    }
}
