package crouskiebackoffice.model;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.dao.DAOStock;
import crouskiebackoffice.view.MainWindow;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelStockTable extends AbstractTableModel {

    private final String[] columnNames = {"Nom", "Couleur", "Taille", "Quantité"};
    private final Class[] columnClass = {String.class, String.class, String.class, Integer.class};

    private List<ProductColorSize> rowData = new LinkedList<>();

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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == getColumnCount() - 1;
    }

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
            dao.insertOrUpdate(productColorSize);
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductColorSize row = rowData.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                row.getProduct().getName();
            case 1 ->
                row.getColor().getName();
            case 2 ->
                row.getSize().getName();
            case 3 ->
                row.getQuantity();
            default ->
                null;
        };
    }

}
