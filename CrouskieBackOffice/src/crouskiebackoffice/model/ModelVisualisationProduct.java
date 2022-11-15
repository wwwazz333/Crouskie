package crouskiebackoffice.model;

import crouskiebackoffice.controle.Navigator;
import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.DAOProduct;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.view.EditProduct;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class ModelVisualisationProduct extends AbstractTableModel {

    private final String[] columnNames = {"Nom", "Déscription", "Prix (en €)", "Collection", "Tailles", "Couleurs", "Tags", "Edit"};
    private final Class[] columnClass = {String.class, String.class, Float.class, String.class, String.class, String.class, String.class, JButton.class};

    private List<Product> rowData = new LinkedList<>();

    public ModelVisualisationProduct(List<Product> allData) {
        rowData = allData;
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
        return columnIndex == getColumnCount() - 1;
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
            case 3:
                if (currentProduct.getCollection() != null) {
                    res = currentProduct.getCollection().getName();
                } else {
                    res = "";
                }
                break;
            case 4:
                res = Arrays.toString(currentProduct.getExistingSize().toArray());
                break;
            case 5:
                res = Arrays.toString(currentProduct.getExistingColor().toArray());
                break;
            case 6:
                res = Arrays.toString(currentProduct.getTags().toArray());
                break;
            case 7:
                JButton b = new JButton();
                b.addActionListener((java.awt.event.ActionEvent evt) -> {
                    //go to edit for this row
                    System.out.println("row = " + rowIndex);
                    Navigator.getInstance().goTo(new EditProduct(currentProduct), "editProduct");
                });
                res = b;
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            rowData.get(rowIndex).setPrice((float) aValue);
        } else {
            super.setValueAt(aValue, rowIndex, columnIndex);
        }
    }

}
