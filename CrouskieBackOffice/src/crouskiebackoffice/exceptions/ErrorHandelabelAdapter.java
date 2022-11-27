package crouskiebackoffice.exceptions;

import crouskiebackoffice.model.OnCrash;

public abstract class ErrorHandelabelAdapter extends Exception implements OnCrash {

    public ErrorHandelabelAdapter(String message) {
        super(message);
    }

    @Override
    public void crashed() {
    }

}
