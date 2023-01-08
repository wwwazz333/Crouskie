package crouskiebackoffice.model;

import crouskiebackoffice.controle.ControllerProductTable;
import java.util.LinkedList;
import java.util.*;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 * Le modèle de la table pour visualisé les produits
 */
public class ModelVisualisationProduct extends AbstractTableModel {

    /**
     * Le nom des colonne
     */
    private final String[] columnNames = {"Nom", "Déscription", "Prix (en €)", "Collection", "Tailles", "Couleurs", "Tags", "Edit"};
    /**
     * Le type des colonne
     */
    private final Class[] columnClass = {String.class, String.class, Float.class, String.class, String.class, String.class, String.class, JButton.class};

    /**
     * Les donnée dans le tableau
     */
    private List<Product> rowData = new LinkedList<>();
    ControllerProductTable controller;

    public ModelVisualisationProduct(ControllerProductTable controller, List<Product> allData) {
        this.controller = controller;
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

    /**
     * récuprer le nom d'une colonne
     * @param columnIndex l'indice de la colonne
     * @return le nom de la colonne
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    /**
     * récuprer la Class d'une colonne
     * @param columnIndex l'indice de la colonne
     * @return la Class de la colonne
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
/**
 * 
 * @param rowIndex l'indice de la ligne
 * @param columnIndex l'indice de la collone
 * @return true si la cellule peut être édité
 */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == getColumnCount() - 1;
    }

    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @return La valeur à l'emplacmenet [{@link rowIndex}, {@link columnIndex}]
     */
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
                    controller.goToEdit(currentProduct);
                });
                res = b;
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }

    /**
     * Modifie la valeur à l'emplacement [{@link rowIndex}, {@link columnIndex}]
     * @param aValue la nouvelle valeur
     * @param rowIndex l'indice de la ligne
     * @param columnIndex l'indice de la colonne
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            rowData.get(rowIndex).setPrice((float) aValue);
        } else {
            super.setValueAt(aValue, rowIndex, columnIndex);
        }
    }

    /**
     * Changer toutes les donnée
     * @param data les nouvelle donnée du tableau
     */
    public void setData(List<Product> data) {
        rowData = data;
    }

}
