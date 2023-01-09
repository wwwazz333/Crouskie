package crouskiebackoffice.view;

import crouskiebackoffice.model.Picture;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Popup pour choisir une image sur l'ordinateur
 */
public class PopupMenuImage extends JPopupMenu {

    private Picture picture;
    private JMenuItem deleteItem;
    private JMenuItem editItem;

    public PopupMenuImage(Picture picture, Runnable edit, Runnable remove) {
        this.picture = picture;

        editItem = new JMenuItem("Éditer");
        deleteItem = new JMenuItem("Supprimer");

        editItem.addActionListener((ae) -> {
            edit.run();
        });
        deleteItem.addActionListener((ae) -> {
            remove.run();
        });

        add(editItem);
        add(deleteItem);
    }

}
