package crouskiebackoffice.model.creation;

import crouskiebackoffice.model.ClothSize;

public class CreateSize implements ICreateWithName<ClothSize> {

    @Override
    public ClothSize createWithName(String name) {
        return new ClothSize(name);
    }
}
