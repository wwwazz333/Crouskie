package crouskiebackoffice.model;

import java.util.List;
import javax.swing.DefaultListModel;

public class DynamicListModel<T extends HasName> extends DefaultListModel<Object> {

    public static final String ajoutLabel = "Ajouté...";

    private List<T> namedList;

    public DynamicListModel(List<T> namedList, DAO daoForAdding) {
        this.namedList = namedList;
        for (HasName named : namedList) {
            addElement(named.getName());
        }

        addElement(DynamicListModel.ajoutLabel);
    }

    @Override
    public void add(int index, Object element) {
        namedList.add((T) element);
        super.add(index, element);
    }

    public List<T> getNamedList() {
        return namedList;
    }

}
