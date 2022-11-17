package crouskiebackoffice.model.listmodel;

import crouskiebackoffice.controle.AddingController;
import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.Tag;
import crouskiebackoffice.model.dao.DAOClothSize;

public class DynamicListSizeModel extends DynamicListModel<ClothSize> {

    public DynamicListSizeModel(Product product) {
        super(product.getExistingSize());

        dao = new DAOClothSize();
    }

    @Override
    public void add() {
        add(getSize() - 1, (new AddingController<Tag>(dao)).newValue());
    }

    @Override
    public String toString() {
        return "DynamicListSizeModel";
    }

}
