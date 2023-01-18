package crouskiebackoffice.view;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.DataStock;
import crouskiebackoffice.model.ModelStockTable;
import crouskiebackoffice.model.Observer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Table pour visualisé les stocks et les édité
 */
public class StockTableView extends JTable implements Observer {

    class StockTableCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Integer quantity = (Integer) table.getModel().getValueAt(row, table.getColumnCount() - 1); // récuper la valeur de la dernière column (quantité)
            if (quantity != null && quantity <= 5) {
                c.setBackground(java.awt.Color.RED);
            } else {
                c.setBackground(java.awt.Color.WHITE);
            }
            c.setForeground(java.awt.Color.BLACK);
            return c;
        }
    }

    public StockTableView() {
    }

    void init() {
        update();
    }

    @Override
    public void update() {
        ErrorHandeler.getInstance().exec(() -> {
            setModel(new ModelStockTable(DataStock.getInstance().getData()));
            setDefaultRenderer(Object.class, new StockTableCellRenderer());
            return true;
        });
    }

}
