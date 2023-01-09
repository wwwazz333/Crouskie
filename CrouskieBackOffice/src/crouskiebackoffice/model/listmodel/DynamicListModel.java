package crouskiebackoffice.model.listmodel;

import crouskiebackoffice.model.dao.DAO;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 * Classe permettant de gérer une liste dynamique d'objets.
 *
 * @param <Object> type d'objets contenus dans la liste.
 */
public abstract class DynamicListModel<Object> extends DefaultListModel<Object> {

    /**
     * DAO qui stock les donnée à afficher dans la liste.
     */
    protected DAO dao;

    /**
     * Constructeur de la classe.
     *
     * @param list liste d'objets à mettre dans la liste.
     */
    public DynamicListModel(List<Object> list) {
        // Création de la liste de données à partir de la liste en paramètre
        // Si la liste en paramètre est nulle, création d'une liste vide
        List<Object> listData;
        if (list != null) {
            listData = new LinkedList<>(list);
        } else {
            listData = new LinkedList<>();
        }

        for (Object named : listData) {
            addElement(named);
        }
    }

    /**
     * Méthode permettant de récupérer les données de la liste.
     *
     * @return liste des données contenues dans la liste.
     */
    public List<Object> getData() {
        List<Object> listData = new LinkedList<>();
        for (int i = 0; i < getSize(); i++) {
            listData.add(getElementAt(i));
        }
        return listData;
    }

    /**
     * Ajoute un élément à la liste. La méthode doit être surchargée par une
     * classe fille afin de déterminer la façon dont l'élément doit être ajouté
     * à la liste.
     */
    public abstract void addItem();
}
