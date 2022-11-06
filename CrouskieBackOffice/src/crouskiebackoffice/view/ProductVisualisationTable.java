package crouskiebackoffice.view;

import crouskiebackoffice.controle.ModelVisualisationProduct;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class ProductVisualisationTable extends JTable {

    public ProductVisualisationTable() {
        setModel(new ModelVisualisationProduct());
        getColumn(getColumnName(getColumnCount() - 1)).setCellRenderer(new ButtonRenderer());
        getColumn(getColumnName(getColumnCount() - 1)).setCellEditor(new ButtonEditor());
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return (Component) value;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        public ButtonEditor() {
            super(new JCheckBox());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            return (Component) value;
        }

    }

}
