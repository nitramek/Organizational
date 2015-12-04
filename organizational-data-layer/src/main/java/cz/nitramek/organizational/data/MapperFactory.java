package cz.nitramek.organizational.data;

public abstract class MapperFactory {

    public abstract <T> Mapper<T> createMapper(Class<T> mapperClassName);
}
