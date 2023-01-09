package crouskiebackoffice.view;

import crouskiebackoffice.controle.AddingController;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.creation.ICreateWithName;

public class AddingColorDialog extends AddingDialog<Color>{

    public AddingColorDialog(AddingController addingController, ICreateWithName createWithName) {
        super(addingController, createWithName);
    }

}
