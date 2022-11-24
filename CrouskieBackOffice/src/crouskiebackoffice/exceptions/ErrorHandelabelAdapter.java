package crouskiebackoffice.exceptions;

import crouskiebackoffice.model.CallbackState;

public abstract class ErrorHandelabelAdapter extends Exception implements CallbackState {

    @Override
    public  void succes(){}

    @Override
    public  void crashed(){}

}
