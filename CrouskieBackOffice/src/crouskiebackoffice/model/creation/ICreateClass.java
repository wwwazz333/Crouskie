package crouskiebackoffice.model.creation;

/**
 * Interface pour crée des Class certaine class
 */
public interface ICreateClass<T> {
    public T createWithName(String name);
    public T createWithNameAndInfo(String name, String info);
}
