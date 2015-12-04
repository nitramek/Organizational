package cz.nitramek.organizational.data.util;

/**
 * Created by Martin on 4.12.2015.
 */
public class MapperCreationException extends Exception {
    public MapperCreationException(Throwable cause) {
        super(cause);
    }

    public MapperCreationException(String message) {
        super(message);
    }

    public MapperCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
