package cz.nitramek.organizational.data.util;

import java.io.IOException;
import java.util.Properties;

public final class MapperFactory {

    public static final String PROPERTIES_PATH = "META-INF/mapper.properties";
    public static final String PACKAGE = "mapper.package";

    /**
     * Creates concrete mapper via configuration.
     *
     * @param mapperClass which mapper
     * @param <T>         Object that mapper handles
     * @return instantiated mapper
     */
    @SuppressWarnings("unchecked")
    public static <T> T createMapper(Class<T> mapperClass) throws MapperCreationException {
        Properties p = new Properties();
        try {

            p.load(MapperFactory.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH));

            String packageName = p.getProperty(PACKAGE);
            DataImplementation di = Class.forName(packageName + ".package-info").getAnnotation(DataImplementation.class);
            for (Class<?> mapperImpl : di.implementations()) {
                MapperImplementation mi = mapperImpl.getAnnotation(MapperImplementation.class);
                if (mi.mapper().equals(mapperClass)) {
                    try {
                        return (T) mapperImpl.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                        throw new MapperCreationException(e);
                    }
                }
            }
//            return (T) concreteFactoryClass.getMethod("createMapper", mapperClass.getClass()).invoke(null, mapperClass);

        } catch (IOException e) {
            throw new MapperCreationException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new MapperCreationException("umm");

    }

    private MapperFactory() {

    }
}
