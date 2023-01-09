package crouskiebackoffice.model.creation;

/**
 * Interface pour crée des Class avec un nom
 */
public interface ICreateWithName<T> {
    public T createWithName(String name);
}
