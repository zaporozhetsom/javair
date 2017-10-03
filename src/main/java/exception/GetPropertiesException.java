package exception;

/**
 * Created by zom on 03.10.2017.
 */
public class GetPropertiesException extends RuntimeException {
    public GetPropertiesException() {
    }

    public GetPropertiesException(String message) {
        super(message);
    }

    public GetPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetPropertiesException(Throwable cause) {
        super(cause);
    }
}
