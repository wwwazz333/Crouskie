package crouskiebackoffice.exceptions;

import crouskiebackoffice.model.OnCrash;

public abstract class ErrorHandelabelAdapter extends Exception implements OnCrash {

    @Override
    public void crashed() {
    }

}
