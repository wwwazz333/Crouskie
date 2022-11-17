package crouskiebackoffice.model.listmodel;

import crouskiebackoffice.model.HasName;
import crouskiebackoffice.model.dao.DAO;
import java.util.List;
import javax.swing.DefaultListModel;

public abstract class DynamicListModel<T extends HasName> extends DefaultListModel<Object> {

    public static final String ajoutLabel = "Ajouté...";
    protected DAO dao;

    List<T> listData;

    public DynamicListModel(List<T> list) {
        listData = list;
        for (HasName named : list) {
            addElement(named.getName());
        }
        addElement(DynamicListModel.ajoutLabel);

    }

    @Override
    public void add(int index, Object element) {
        listData.add((T) element);
        super.add(index, element);
    }

    public List<T> getNamedList() {
        return listData;
    }

    public abstract void add();

}
