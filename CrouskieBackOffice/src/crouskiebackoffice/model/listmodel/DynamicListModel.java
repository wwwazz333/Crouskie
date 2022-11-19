package crouskiebackoffice.model.listmodel;

import crouskiebackoffice.model.HasName;
import crouskiebackoffice.model.dao.DAO;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;

public abstract class DynamicListModel<Object> extends DefaultListModel<Object> {

    protected DAO dao;

    public DynamicListModel(List<Object> list) {
        List<Object> listData = new LinkedList<>(list);
        for (Object named : listData) {
            addElement(named);
        }
    }

    public List<Object> getData() {
        List<Object> listData = new LinkedList<>();
        for (int i = 0; i < getSize(); i++) {
            listData.add(getElementAt(i));
        }
        return listData;
    }

    public abstract void addItem();

}
