package cz.nitramek.organizational.data.util;

import java.io.IOException;
import java.io.InputStream;
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

            InputStream stream = MapperFactory.class.getClassLoader().getResource(PROPERTIES_PATH).openStream();
            p.load(stream);
            stream.close();

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

        } catch (IOException | NullPointerException e) {
            throw new MapperCreationException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new MapperCreationException("umm");

    }

    private MapperFactory() {

    }
}
