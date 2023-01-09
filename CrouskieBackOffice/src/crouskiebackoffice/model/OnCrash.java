package crouskiebackoffice.model;

/**
 * Interface qui s'occupe d'effectué une tâche en cas de crash (erreur levé)
 */
public interface OnCrash {

    /**
     * Quand une erreur est levé
     */
    public void crashed();

}
