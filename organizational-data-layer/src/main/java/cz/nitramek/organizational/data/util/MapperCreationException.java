package cz.nitramek.organizational.data.util;


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
