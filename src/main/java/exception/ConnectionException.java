package exception;

/**
 * Created by zom on 30.09.2017.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
