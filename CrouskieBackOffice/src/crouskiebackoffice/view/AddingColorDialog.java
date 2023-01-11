package crouskiebackoffice.view;

import crouskiebackoffice.controle.AddingController;
import crouskiebackoffice.model.Color;
import javax.swing.JTextField;
import crouskiebackoffice.model.creation.ICreateClass;

public class AddingColorDialog extends AddingDialog<Color> {

    private JTextField codeColorField;

    public AddingColorDialog(AddingController addingController, ICreateClass createWithName) {
        super(addingController, createWithName);
        codeColorField = new JTextField();

        codeColorField.setMaximumSize(new java.awt.Dimension(400, 40));
        codeColorField.setPreferredSize(new java.awt.Dimension(200, 25));

        panelField.add(codeColorField);
    }

    /**
     * 
     * @return le textfield pour le code de la couleur.
     */
    public JTextField getCodeColorField() {
        return codeColorField;
    }

}
