package crouskiebackoffice.view;

import crouskiebackoffice.controle.ModelVisualisationProduct;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ProductVisualisationTable extends JTable {

    public ProductVisualisationTable() {
        setModel(new ModelVisualisationProduct());
        getColumn(getColumnName(getColumnCount() - 1)).setCellRenderer(new ButtonRenderer());
        getColumn(getColumnName(getColumnCount() - 1)).setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Modify" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        private String label;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "Modify" : value.toString();

            JButton button = new JButton(label);
            return button;
        }

        public Object getCellEditorValue() {
            return new String(label);
        }
    }

}
