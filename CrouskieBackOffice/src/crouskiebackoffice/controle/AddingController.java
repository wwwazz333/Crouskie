package crouskiebackoffice.controle;

import crouskiebackoffice.model.creation.ICreateWithName;
import crouskiebackoffice.model.dao.DAO;
import crouskiebackoffice.view.AddingDialog;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddingController<T> {

    private DAO dao;
    private ICreateWithName createWithName;
    private AddingDialog<T> addingDialog;

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

    public List<T> getData() {
        try {
            return dao.getAllData();
        } catch (SQLException ex) {
            Logger.getLogger(AddingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LinkedList<>();
    }

    /**
     *
     * @param name the name for the new Value
     * @return true if the new Value has been succesfully created
     */
    public boolean createValue(String name) {
        if (name != null && !name.isBlank()) {
            try {
                dao.insertOrUpdate(createWithName.createWithName(name));
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(AddingController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }
}
