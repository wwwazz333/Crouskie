package crouskiebackoffice.model.listmodel;

import crouskiebackoffice.controle.AddingController;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.Tag;
import crouskiebackoffice.model.creation.CreateTag;
import crouskiebackoffice.model.dao.DAOTag;

/**
 * Liste dynamic pour les données de type {@link Tag}
 */
public class DynamicListTagModel extends DynamicListModel<Tag> {

    public DynamicListTagModel(Product product) {
        super(product.getTags());

        dao = new DAOTag();
    }

    @Override
    public void addItem() {
        add(getSize(), (new AddingController<Tag>(dao, new CreateTag())).newValue());
    }

    @Override
    public String toString() {
        return "DynamicListTagModel";
    }
    
    

}
