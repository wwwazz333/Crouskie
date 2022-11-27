package crouskiebackoffice.model;

import java.util.LinkedList;
import java.util.List;

public abstract class Notifier {

    private final List<Observer> observers;

    public Notifier() {
        this.observers = new LinkedList<>();
    }

    /**
     * Notify all the observers super.notif() must be called at the end of the
     * override
     */
    public void notif() throws Exception {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
}
