package crouskiebackoffice.model.listmodel;

import crouskiebackoffice.controle.AddingController;
import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.creation.CreateSize;
import crouskiebackoffice.model.dao.DAOClothSize;

public class DynamicListSizeModel extends DynamicListModel<ClothSize> {

    public DynamicListSizeModel(Product product) {
        super(product.getExistingSize());

        dao = new DAOClothSize();
    }

    @Override
    public void addItem() {
        add(getSize(), (new AddingController<ClothSize>(dao, new CreateSize())).newValue());
    }

    @Override
    public String toString() {
        return "DynamicListSizeModel";
    }

}
