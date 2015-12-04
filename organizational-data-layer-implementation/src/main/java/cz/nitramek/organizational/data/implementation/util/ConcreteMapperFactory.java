package cz.nitramek.organizational.data.implementation.util;


import cz.nitramek.organizational.data.util.DataImplementation;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.data.util.MapperImplementation;

public class ConcreteMapperFactory {
    public static <T> T createMapper(Class<T> mapperClass) throws MapperCreationException {
        Package[] packages = Package.getPackages();
        for (Package p : packages) {
            DataImplementation dataImpl = p.getAnnotation(DataImplementation.class);
            if (dataImpl != null) {
                for (Class<?> mapperClassImpl : dataImpl.implementations()) {
                    MapperImplementation mapperImpl = mapperClassImpl.getAnnotation(MapperImplementation.class);
                    if (mapperImpl != null) {
                        if (mapperImpl.mapper().equals(mapperClass)) {
                            try {
                                return (T) mapperClassImpl.newInstance();
                            } catch (InstantiationException | IllegalAccessException e) {
                                throw new MapperCreationException(e);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
