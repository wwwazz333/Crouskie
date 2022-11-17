package crouskiebackoffice.model.listmodel;

import crouskiebackoffice.controle.AddingController;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.Tag;
import crouskiebackoffice.model.dao.DAOColor;

public class DynamicListColorModel extends DynamicListModel<Color> {

    public DynamicListColorModel(Product product) {
        super(product.getExistingColor());

        dao = new DAOColor();
    }

    @Override
    public void add() {
        add(getSize() - 1, (new AddingController<Tag>(dao)).newValue());
    }
    
    @Override
    public String toString() {
        return "DynamicListColorModel";
    }

}
