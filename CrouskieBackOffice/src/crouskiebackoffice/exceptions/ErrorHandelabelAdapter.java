package crouskiebackoffice.exceptions;

import crouskiebackoffice.model.OnCrash;

/**
 * Exception abstraite utilisée pour gérer les erreurs avec une action à execute
 * en cas de crash.
 */
public abstract class ErrorHandelabelAdapter extends Exception implements OnCrash {

    /**
     * Crée une nouvelle instance de ErrorHandelabelAdapter.
     *
     * @param message Le message décrivant l'erreur
     */
    public ErrorHandelabelAdapter(String message) {
        super(message);
    }

    @Override
    public void crashed() {
    }
}
