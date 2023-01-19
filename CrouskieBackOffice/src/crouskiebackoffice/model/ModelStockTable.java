package crouskiebackoffice.model;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.dao.DAOStock;
import crouskiebackoffice.view.MainWindow;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Classe modélisant un modèle de table pour le stock. Cette classe hérite
 * d'AbstractTableModel et implémente les méthodes.
 */
public class ModelStockTable extends AbstractTableModel {

    /**
     *
     * Nom des colonnes de la table
     */
    private final String[] columnNames = {"Nom", "Couleur", "Taille", "Quantité"};
    /**
     *
     * Types de données de chaque colonne de la table
     */
    private final Class[] columnClass = {String.class, String.class, String.class, Integer.class};

    /**
     *
     * Liste de données à afficher dans la table
     */
    private List<ProductColorSize> rowData = new LinkedList<>();

    /**
     *
     * Constructeur de la classe.
     *
     * @param rowData liste de données à afficher dans la table
     */
    public ModelStockTable(List<ProductColorSize> rowData) {
        this.rowData = rowData;
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

    /**
     *
     * @param rowIndex la ligne à modifier
     * @param columnIndex la colone de la ligne à modifier
     *
     * @return true si la cellule à l'emplacement
     * [{@link rowIndex}, {@link columnIndex}] peut être édité
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == getColumnCount() - 1;
    }

    /**
     * modifie la valeur de la case [{@link rowIndex}, {@link columnIndex}]
     *
     * @param aValue la nouvelle valeur
     * @param rowIndex la ligne à modifier
     * @param columnIndex la colone de la ligne à modifier
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
        ProductColorSize productColorSize = rowData.get(rowIndex);
        Integer previousQuatity = productColorSize.getQuantity();

        MainWindow.instance.getStatusbar().setLoading(true);
        MainWindow.instance.getStatusbar().showMsg("Envoi des données");
        boolean succes = ErrorHandeler.getInstance().exec(() -> {

            if (aValue != null) {
                productColorSize.setQuantity(Integer.parseInt(aValue.toString()));
            }

            DAOStock dao = new DAOStock();
            dao.insertOrUpdate(productColorSize, false);
            return true;
        });
        MainWindow.instance.getStatusbar().setLoading(false);
        if (succes) {
            MainWindow.instance.getStatusbar().showMsg("Succes", 1000);
        } else {
            MainWindow.instance.getStatusbar().showMsg("Erreur de l'envoi des données", 2000);
            productColorSize.setQuantity(previousQuatity);
        }

        //s'assure que l'ui est à jour
        ErrorHandeler.getInstance().exec(() -> {
            DataStock.getInstance().notif();
            return true;
        });
    }

    /**
     * récuper la veleur d'une case du tableau
     *
     * @param rowIndex la ligne du tableau
     * @param columnIndex la collone du tableau
     * @return La valeur à la case [{@link rowIndex}, {@link columnIndex}]
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductColorSize row = rowData.get(rowIndex);
        Object res = null;
        switch (columnIndex) {
            case 0:
                return row.getProduct().getName();
            case 1:
                return row.getColor().getName();
            case 2:
                return row.getSize().getName();
            case 3:
                return res = row.getQuantity();
            default:
                return res;
        }
    }
}
