package crouskiebackoffice.controle;

public class Navigator {

    private static Navigator instance;

    static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }
}
