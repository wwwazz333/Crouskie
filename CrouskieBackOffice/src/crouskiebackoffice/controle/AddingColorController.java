package crouskiebackoffice.controle;

import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.dao.DAO;
import crouskiebackoffice.view.AddingColorDialog;
import crouskiebackoffice.model.creation.ICreateClass;

public class AddingColorController extends AddingController<Color> {

    public AddingColorController(DAO dao, ICreateClass creator) {
        super(dao, creator);
    }

    @Override
    public Color newValue() {
        addingDialog = new AddingColorDialog(this, classCreator);
        return addingDialog.getResult();
    }

    @Override
    public boolean createValue() {
        if (addingDialog instanceof AddingColorDialog addingColorDialog) {
            String name = addingColorDialog.getDefaultInputField().getText();
            String code = addingColorDialog.getCodeColorField().getText();

            if (name != null && !name.isBlank()) {
                return ErrorHandeler.getInstance().exec(() -> {
                    dao.insertOrUpdate(classCreator.createWithNameAndInfo(name, code), false);
                    return true;
                });
            }
        }
        return false;
    }

}
