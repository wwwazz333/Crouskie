package crouskiebackoffice.controle;

import crouskiebackoffice.model.DataStock;
import crouskiebackoffice.model.ProductColorSize;
import crouskiebackoffice.view.StockTableView;
import java.util.LinkedList;
import java.util.List;

public class ControllerStockTable {

    private StockTableView view;

    List<ProductColorSize> data = new LinkedList<>();

    public ControllerStockTable(StockTableView view) {
        this.view = view;

    }

    public List<ProductColorSize> getAllData() {
        ErrorHandeler.getInstance().exec(() -> {
            setData(DataStock.getInstance().getData());
            return true;
        });

        return data;
    }

    public void setData(List<ProductColorSize> data) {
        this.data = data;
    }

}
