package crouskiebackoffice.view;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.model.DataStock;
import crouskiebackoffice.model.ModelStockTable;
import crouskiebackoffice.model.Observer;
import javax.swing.JTable;

public class StockTableView extends JTable implements Observer {

    public StockTableView() {
    }

    void init() {
        ErrorHandeler.getInstance().exec(() -> {
            setModel(new ModelStockTable(DataStock.getInstance().getData()));
            return true;
        });
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
