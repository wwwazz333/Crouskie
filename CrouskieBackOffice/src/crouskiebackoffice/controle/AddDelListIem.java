package crouskiebackoffice.controle;

import javax.swing.JList;

/**
 * Interface permettant de définir les fonctions add et del pour une JList.
 */
public interface AddDelListIem {

    /**
     * Permet d'ajouter un élément à une JList.
     *
     * @param jlist la JList à laquelle ajouter l'élément
     */
    public void add(JList jlist);

    /**
     * Permet de supprimer un élément d'une JList.
     *
     * @param jlist la JList à laquelle supprimer l'élément
     */
    public void del(JList jlist);
}
