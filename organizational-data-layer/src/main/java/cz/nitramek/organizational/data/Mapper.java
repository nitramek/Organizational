package cz.nitramek.organizational.data;

/**
 * Default mapper defining concrete mapper operations
 *
 * @param <Type> which type is mapper mapping
 */
public interface Mapper<Type> {
    Type insert(Type type);

    Type update(Type type);

    Type select(long id);
}
