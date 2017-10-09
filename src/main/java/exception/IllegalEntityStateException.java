package exception;

/**
 * Created by zom on 08.10.2017.
 */
public class IllegalEntityStateException extends Exception {
    public IllegalEntityStateException() {
    }

    public IllegalEntityStateException(String message) {
        super(message);
    }

    public IllegalEntityStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEntityStateException(Throwable cause) {
        super(cause);
    }
}
