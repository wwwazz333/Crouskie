package crouskiebackoffice.model.creation;

import crouskiebackoffice.model.Color;

public class CreateColor  implements ICreateWithName<Color> {
    @Override
    public Color createWithName(String name) {
        return new Color(name);
    }
}
