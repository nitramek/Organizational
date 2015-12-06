package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.Mapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.data.util.MapperFactory;

public abstract class AbstractService<K, T extends Mapper<K>> implements Service<K> {


    protected T mapper;
    private Class<K> objClass;


    protected void init(Class<K> objClass, Class<T> mapperClass) throws MapperCreationException {
        this.mapper = MapperFactory.createMapper(mapperClass);
        this.objClass = objClass;
    }


    @Override
    public K create() {
        try {
            return objClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public K add(K type) {
        return this.mapper.insert(type);
    }

    @Override
    public K update(K type) {
        return this.mapper.update(type);
    }

    @Override
    public K get(long id) {
        return this.mapper.select(id);
    }
}
