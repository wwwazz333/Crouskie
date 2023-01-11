package crouskiebackoffice.controle;

import crouskiebackoffice.model.dao.DAO;
import crouskiebackoffice.view.AddingDialog;
import java.util.LinkedList;
import java.util.List;
import crouskiebackoffice.model.creation.ICreateClass;

public class AddingController<T> {

    protected DAO dao;
    protected ICreateClass classCreator;
    protected AddingDialog<T> addingDialog;

    public AddingController(DAO dao, ICreateClass createWithName) {
        this.dao = dao;
        this.classCreator = createWithName;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public T newValue() {
        addingDialog = new AddingDialog<>(this, classCreator);
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
     * @return true if the new Value has been succesfully created
     */
    public boolean createValue() {
        String name = addingDialog.getDefaultInputField().getText();
        if (name != null && !name.isBlank()) {
            return ErrorHandeler.getInstance().exec(() -> {
                dao.insertOrUpdate(classCreator.createWithName(name));
                return true;
            });
        }
        return false;
    }
}
