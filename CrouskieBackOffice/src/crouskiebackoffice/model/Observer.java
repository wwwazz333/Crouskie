package crouskiebackoffice.model;

/**
 * Observateur pour le disigne pattern Observer (observer doit être attacher à une class Notifier).
 */
public interface Observer {

    /**
     * Mettre à jours les données
     */
    public void update();
}
