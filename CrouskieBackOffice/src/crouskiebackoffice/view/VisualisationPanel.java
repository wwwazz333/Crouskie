package crouskiebackoffice.view;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.DataProduct;
import crouskiebackoffice.model.IUpdate;
import crouskiebackoffice.model.Observer;
import java.awt.event.ItemEvent;

/**
 * Panel qui contien La table de visualisation
 */
public class VisualisationPanel extends javax.swing.JPanel implements Observer, IUpdate {

    /**
     * Creates new form VisualisationPanel
     */
    public VisualisationPanel() {
        initComponents();

        displayOutSale.addItemListener((ItemEvent e) -> {
            productVisualisationTable.getControlle().setDisplayOutSale(e.getStateChange() == ItemEvent.SELECTED);//true si le boutton passe en Activé
            update();
        });

        try {
            ErrorHandeler.getInstance().exec(() -> {
                DataProduct.getInstance().registerObserver(this);
                return true;
            });
        } catch (Exception e) {
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        productVisualisationTable = new crouskiebackoffice.view.ProductVisualisationTable();
        jPanel1 = new javax.swing.JPanel();
        displayOutSale = new javax.swing.JToggleButton();

        setLayout(new java.awt.BorderLayout());

        jScrollPane2.setViewportView(productVisualisationTable);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        displayOutSale.setText("Afficher Hors Vente");
        displayOutSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayOutSaleActionPerformed(evt);
            }
        });
        jPanel1.add(displayOutSale, java.awt.BorderLayout.EAST);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void displayOutSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayOutSaleActionPerformed

    }//GEN-LAST:event_displayOutSaleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton displayOutSale;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private crouskiebackoffice.view.ProductVisualisationTable productVisualisationTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        revalidate();
        repaint();
        productVisualisationTable.revalidate();
        productVisualisationTable.repaint();
    }
}
