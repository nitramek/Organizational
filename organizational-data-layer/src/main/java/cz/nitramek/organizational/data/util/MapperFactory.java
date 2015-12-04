package cz.nitramek.organizational.data.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public final class MapperFactory {

    /**
     * Creates concrete mapper via configuration.
     *
     * @param mapperClass which mapper
     * @param <T>         Object that mapper handles
     * @return instantiated mapper
     */
    public static <T> T createMapper(Class<T> mapperClass) throws MapperCreationException {
        Properties p = new Properties();
        try {

            p.load(MapperFactory.class.getClassLoader().getResourceAsStream("META-INF/mapper.properties"));
            String concreteMapperFactory = p.getProperty("mapper.implementation");
            Class<?> concreteFactoryClass = Class.forName(concreteMapperFactory);
            return (T) concreteFactoryClass.getMethod("createMapper", mapperClass.getClass()).invoke(null, mapperClass);

        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new MapperCreationException(e);
        }
    }

    private MapperFactory() {

    }
}
