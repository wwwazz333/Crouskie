package crouskiebackoffice.view;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel1 = new crouskiebackoffice.view.HeaderPanel();
        mainPane = new javax.swing.JPanel();
        tabPane = new javax.swing.JTabbedPane();
        visualisationPanel1 = new crouskiebackoffice.view.VisualisationPanel();
        addingPanel1 = new crouskiebackoffice.view.AddingPanel();
        editionPanel1 = new crouskiebackoffice.view.EditionPanel();
        statusbarPanel1 = new crouskiebackoffice.view.StatusbarPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crouskie BackOffice");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(400, 157));
        setPreferredSize(new java.awt.Dimension(720, 480));
        getContentPane().add(headerPanel1, java.awt.BorderLayout.NORTH);

        mainPane.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout visualisationPanel1Layout = new javax.swing.GroupLayout(visualisationPanel1);
        visualisationPanel1.setLayout(visualisationPanel1Layout);
        visualisationPanel1Layout.setHorizontalGroup(
            visualisationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        visualisationPanel1Layout.setVerticalGroup(
            visualisationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        tabPane.addTab("Visualiser", visualisationPanel1);

        javax.swing.GroupLayout addingPanel1Layout = new javax.swing.GroupLayout(addingPanel1);
        addingPanel1.setLayout(addingPanel1Layout);
        addingPanel1Layout.setHorizontalGroup(
            addingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        addingPanel1Layout.setVerticalGroup(
            addingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        tabPane.addTab("Ajouter", addingPanel1);

        javax.swing.GroupLayout editionPanel1Layout = new javax.swing.GroupLayout(editionPanel1);
        editionPanel1.setLayout(editionPanel1Layout);
        editionPanel1Layout.setHorizontalGroup(
            editionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        editionPanel1Layout.setVerticalGroup(
            editionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        tabPane.addTab("Éditer", editionPanel1);

        mainPane.add(tabPane, "tabPane");

        getContentPane().add(mainPane, java.awt.BorderLayout.CENTER);
        getContentPane().add(statusbarPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            //Lien pour téléchargé le look and feel : https://search.maven.org/artifact/com.formdev/flatlaf/2.6/jar
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.initSettings();
            mainWindow.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private crouskiebackoffice.view.AddingPanel addingPanel1;
    private crouskiebackoffice.view.EditionPanel editionPanel1;
    private crouskiebackoffice.view.HeaderPanel headerPanel1;
    private javax.swing.JPanel mainPane;
    private crouskiebackoffice.view.StatusbarPanel statusbarPanel1;
    private javax.swing.JTabbedPane tabPane;
    private crouskiebackoffice.view.VisualisationPanel visualisationPanel1;
    // End of variables declaration//GEN-END:variables

    private void initSettings() {
        try {
            setIconImage(ImageIO.read(getClass().getResource("/assets/logo.png")));
        } catch (IOException ex) {
            System.err.println("Error : can't load software Ico");
        }
    }
}
