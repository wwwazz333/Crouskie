package crouskiebackoffice.view;

import crouskiebackoffice.controle.ModelVisualisationProduct;
import java.awt.Color;
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
        getColumn(getColumnName(getColumnCount() - 1)).setCellEditor(new ButtonEditor());

    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof JButton) {
                JButton btn = ((JButton) value);
                btn.setForeground(Color.black);
                btn.setBackground(new Color(128, 128, 128));
                btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ico_edit.png")));
                return btn;
            }
            setForeground(Color.black);
            setBackground(Color.red);
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ico_edit.png")));
            setText((value == null) ? "" : value.toString());
            return this;
        }

    }

    class ButtonEditor extends DefaultCellEditor {

        public ButtonEditor() {
            super(new JCheckBox());
        }

//        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//            this.button = (JButton) value;
//            button.setBackground(Color.red);
//            return this.button;
//        }
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof JButton) {
                JButton btn = ((JButton) value);
                btn.setForeground(Color.black);
                btn.setBackground(new Color(128, 128, 128));
                btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ico_edit.png")));
                return btn;
            }
            return (Component) value;
        }

    }

}
