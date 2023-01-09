package crouskiebackoffice.view;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.DataStock;
import crouskiebackoffice.model.ModelStockTable;
import crouskiebackoffice.model.Observer;
import javax.swing.JTable;

/**
 * Table pour visualisé les stocks et les édité
 */
public class StockTableView extends JTable implements Observer {

    public StockTableView() {
    }

    void init() {
        update();
    }
  

    @Override
    public void update() {
        ErrorHandeler.getInstance().exec(() -> {
            setModel(new ModelStockTable(DataStock.getInstance().getData()));
            return true;
        });
    }

}
