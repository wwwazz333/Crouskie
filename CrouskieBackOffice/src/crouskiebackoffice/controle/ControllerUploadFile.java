package crouskiebackoffice.controle;

import java.awt.Component;
import javax.swing.JFileChooser;

public class ControllerUploadFile {

    private JFileChooser fileChooser;
    private Component parent;

    public ControllerUploadFile(Component parent) {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Upload");
        fileChooser.setMultiSelectionEnabled(false);
    }

    public String choose() {
        int res = fileChooser.showDialog(parent, "upload");
        if(res == JFileChooser.APPROVE_OPTION){
               return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}
