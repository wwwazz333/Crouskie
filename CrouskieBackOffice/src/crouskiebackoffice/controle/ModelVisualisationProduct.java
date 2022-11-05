package crouskiebackoffice.controle;

import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.DAOProduct;
import crouskiebackoffice.model.Product;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class ModelVisualisationProduct extends AbstractTableModel {
    
    private final String[] columnNames = {"Nom", "Déscription", "Prix (en €)"};
    private final Class[] columnClass = {String.class, String.class, Float.class};
    
    private List<Product> rowData = new LinkedList<>();
    
    public ModelVisualisationProduct() {
        DAOProduct dao = new DAOProduct();
        
        try {
            rowData = dao.getAllData("nameprod");
            ConnectionDB.getInstance().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelVisualisationProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public int getRowCount() {
        return rowData.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product currentProduct = rowData.get(rowIndex);
        Object res;
        switch (columnIndex) {
            case 0:
                res = currentProduct.getName();
                break;
            case 1:
                res = currentProduct.getDescription();
                break;
            case 2:
                res = currentProduct.getPrice();
                
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        rowData.get(rowIndex).setPrice((float) aValue);
    }
    
}
