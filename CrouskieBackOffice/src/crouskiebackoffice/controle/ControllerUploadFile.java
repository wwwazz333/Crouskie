package crouskiebackoffice.controle;

import java.awt.Component;
import javax.swing.JFileChooser;

/**
 * Contrôleur pour afficher une boîte de dialogue d'upload de fichier dans une
 * interface utilisateur. Utilise un JFileChooser pour afficher la boîte de
 * dialogue et sélectionner un fichier à uploader.
 */
public class ControllerUploadFile {

    // JFileChooser pour afficher la boîte de dialogue et sélectionner un fichier à uploader
    private JFileChooser fileChooser;
    // Composant parent pour afficher la boîte de dialogue
    private Component parent;

    /**
     * Constructeur pour initialiser le JFileChooser et le titre de la boîte de
     * dialogue.
     *
     * @param parent Composant parent pour afficher la boîte de dialogue
     */
    public ControllerUploadFile(Component parent) {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Upload");
        fileChooser.setMultiSelectionEnabled(false);
    }

    /**
     * Affiche la boîte de dialogue et retourne l'emplacement absolu du fichier
     * sélectionné, ou null s'il n'y a pas de fichier sélectionné.
     *
     * @return L'emplacement absolu du fichier sélectionné, ou null s'il n'y a
     * pas de fichier sélectionné
     */
    public String choose() {
        // Afficher la boîte de dialogue
        int res = fileChooser.showDialog(parent, "upload");
        // Si un fichier est sélectionné, retourner son emplacement absolu
        if (res == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        // Si aucun fichier n'est sélectionné, retourner null
        return null;
    }
}
