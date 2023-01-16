package crouskiebackoffice.view;

import com.formdev.flatlaf.FlatLightLaf;
import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.controle.Navigator;
import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.ConnectionDB;
import crouskiebackoffice.model.Product;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class MainWindow extends javax.swing.JFrame {

    public static MainWindow instance;

    @Override
    public void dispose() {
        try {
            ConnectionDB.getInstance().close();
        } catch (SQLException | ErrorHandelabelAdapter ex) {
            System.err.println("Failed to close connection");
        }
        super.dispose();
        System.exit(0);
    }

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        instance = this;
        ErrorHandeler.getInstance().init(this);
        initComponents();
        stockTable.init();
        initSettings();

        addProductPanel = new EditProduct(new Product("", "", 10f, null));
        tabPane.addTab("Ajouter", addProductPanel);

        tabPane.addChangeListener((ce) -> {
            if (tabPane.getSelectedComponent() == visualisationPanel) {
                visualisationPanel.update();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel1 = new crouskiebackoffice.view.HeaderPanel();
        mainPane = new javax.swing.JPanel();
        onglets = new javax.swing.JTabbedPane();
        tabPane = new javax.swing.JTabbedPane();
        panelVisualisation = new javax.swing.JPanel();
        visualisationPanel = new crouskiebackoffice.view.VisualisationPanel();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        stockPane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stockTable = new crouskiebackoffice.view.StockTableView();
        statusbar = new crouskiebackoffice.view.StatusbarPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crouskie BackOffice");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(900, 500));
        setPreferredSize(new java.awt.Dimension(900, 500));
        getContentPane().add(headerPanel1, java.awt.BorderLayout.NORTH);

        mainPane.setLayout(new java.awt.CardLayout());

        panelVisualisation.setLayout(new java.awt.BorderLayout());
        panelVisualisation.add(visualisationPanel, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jToggleButton1.setText("Afficher Produits Hors Vente");
        jPanel1.add(jToggleButton1, java.awt.BorderLayout.EAST);

        panelVisualisation.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        tabPane.addTab("Éditer", panelVisualisation);

        onglets.addTab("Produits", tabPane);

        stockPane.setLayout(new java.awt.BorderLayout());

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(stockTable);

        stockPane.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        onglets.addTab("Stock", stockPane);

        mainPane.add(onglets, "main");

        getContentPane().add(mainPane, java.awt.BorderLayout.CENTER);
        getContentPane().add(statusbar, java.awt.BorderLayout.SOUTH);

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
            mainWindow.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private crouskiebackoffice.view.HeaderPanel headerPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel mainPane;
    private javax.swing.JTabbedPane onglets;
    private javax.swing.JPanel panelVisualisation;
    private crouskiebackoffice.view.StatusbarPanel statusbar;
    private javax.swing.JPanel stockPane;
    private crouskiebackoffice.view.StockTableView stockTable;
    private javax.swing.JTabbedPane tabPane;
    private crouskiebackoffice.view.VisualisationPanel visualisationPanel;
    // End of variables declaration//GEN-END:variables
    private EditProduct addProductPanel;

    private void initSettings() {
        try {
            setIconImage(ImageIO.read(getClass().getResource("/assets/logo.png")));
        } catch (IOException ex) {
            System.err.println("Error : can't load software Ico");
        }

        Navigator.getInstance().init(mainPane, "main");
    }

    public StatusbarPanel getStatusbar() {
        return statusbar;
    }
}
