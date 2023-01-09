package crouskiebackoffice.model.creation;

import crouskiebackoffice.model.Color;

/**
 * Crée une Color avec sont nom
 */
public class CreateColor  implements ICreateWithName<Color> {
    @Override
    public Color createWithName(String name) {
        return new Color(name);
    }
}
