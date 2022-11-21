package crouskiebackoffice.model.creation;

import crouskiebackoffice.model.Tag;

public class CreateTag implements ICreateWithName<Tag> {
    @Override
    public Tag createWithName(String name) {
        return new Tag(name);
    }
}
