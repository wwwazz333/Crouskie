package crouskiebackoffice.view;

import crouskiebackoffice.model.Picture;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupMenuImage extends JPopupMenu {

    private Picture picture;
    private JMenuItem deleteItem;
    private JMenuItem editItem;

    public PopupMenuImage(Picture picture) {
        this.picture = picture;

        editItem = new JMenuItem("Éditer");
        deleteItem = new JMenuItem("Supprimer");
        
        
        editItem.addActionListener((ae) -> {
            System.out.println("édite...");
        });
        deleteItem.addActionListener((ae) -> {
            System.out.println("suppr...");
        });
        
        
        
        add(editItem);
        add(deleteItem);
    }
    
    

}
