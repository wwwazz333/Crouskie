package crouskiebackoffice.view;

import crouskiebackoffice.controle.AddingController;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.creation.ICreateWithName;
import javax.swing.JTextField;

public class AddingColorDialog extends AddingDialog<Color> {

    public AddingColorDialog(AddingController addingController, ICreateWithName createWithName) {
        super(addingController, createWithName);
        JTextField codeColorField = new JTextField();
        
        codeColorField.setMaximumSize(new java.awt.Dimension(400, 40));
        codeColorField.setPreferredSize(new java.awt.Dimension(200, 25));

        panelField.add(codeColorField);
    }

}
