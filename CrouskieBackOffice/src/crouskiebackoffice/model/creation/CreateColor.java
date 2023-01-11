package crouskiebackoffice.model.creation;

import crouskiebackoffice.model.Color;

/**
 * Crée une Color avec sont nom
 */
public class CreateColor  implements ICreateClass<Color> {
    @Override
    public Color createWithName(String name) {
        return new Color(name);
    }

    @Override
    public Color createWithNameAndInfo(String name, String info) {
        return new Color(name, info);
    }
}
