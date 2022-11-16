package crouskiebackoffice.controle;

import crouskiebackoffice.model.DAO;
import crouskiebackoffice.view.AddingDialog;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddingController<T> {

    DAO dao;
    Frame parent;

    public AddingController(DAO dao, Frame parent) {
        this.dao = dao;
        this.parent = parent;
    }

    public T newValue() {
        AddingDialog<T> addingDialog = new AddingDialog<>(parent, this);
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
}
