package crouskiebackoffice.controle;

import crouskiebackoffice.model.creation.ICreateWithName;
import crouskiebackoffice.model.dao.DAO;
import crouskiebackoffice.view.AddingDialog;
import java.util.LinkedList;
import java.util.List;

public class AddingController<T> {

    private DAO dao;
    protected ICreateWithName createWithName;
    protected AddingDialog<T> addingDialog;

    public AddingController(DAO dao, ICreateWithName createWithName) {
        this.dao = dao;
        this.createWithName = createWithName;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public T newValue() {
        addingDialog = new AddingDialog<>(this, createWithName);
        return addingDialog.getResult();
    }

    List<T> data = new LinkedList<>();

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        ErrorHandeler.getInstance().exec(() -> {
            setData(dao.getAllData());
            return true;
        });
        return data;
    }

    /**
     *
     * @param name the name for the new Value
     * @return true if the new Value has been succesfully created
     */
    public boolean createValue(String name) {
        if (name != null && !name.isBlank()) {
            return ErrorHandeler.getInstance().exec(() -> {
                dao.insertOrUpdate(createWithName.createWithName(name));
                return true;
            });
        }
        return false;
    }
}
