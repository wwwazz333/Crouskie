package crouskiebackoffice.controle;

import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.creation.ICreateWithName;
import crouskiebackoffice.model.dao.DAO;
import crouskiebackoffice.view.AddingColorDialog;

public class AddingColorController extends AddingController<Color> {

    public AddingColorController(DAO dao, ICreateWithName createWithName) {
        super(dao, createWithName);
    }

    @Override
    public Color newValue() {
        addingDialog = new AddingColorDialog(this, createWithName);
        return addingDialog.getResult();
    }

}
