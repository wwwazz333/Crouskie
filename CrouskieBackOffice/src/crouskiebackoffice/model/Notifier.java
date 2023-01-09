package crouskiebackoffice.model;

import java.util.LinkedList;
import java.util.List;

/**
 * class Notfier du disigne pattern Observer
 */
public abstract class Notifier {

    /**
     * Les observers attacher
     */
    private final List<Observer> observers;

    public Notifier() {
        this.observers = new LinkedList<>();
    }

    /**
     * Notifi tous les observers attacher
     */
    public void notif() throws Exception {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * attacher un observer à cette class
     * @param observer l'observer à ajouté
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
}
