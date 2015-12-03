package cz.nitramek.organizational.data;


public interface Mapper<Type> {
    Type insert(Type type);

    Type update(Type type);

    Type select(long id);
}
