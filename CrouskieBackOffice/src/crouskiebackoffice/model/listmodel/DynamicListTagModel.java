package crouskiebackoffice.model.listmodel;

import crouskiebackoffice.controle.AddingController;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.Tag;
import crouskiebackoffice.model.dao.DAOTag;

public class DynamicListTagModel extends DynamicListModel<Tag> {

    public DynamicListTagModel(Product product) {
        super(product.getTags());

        dao = new DAOTag();
    }

    @Override
    public void addItem() {
        add(getSize(), (new AddingController<Tag>(dao)).newValue());
    }

    @Override
    public String toString() {
        return "DynamicListTagModel";
    }
    
    

}
