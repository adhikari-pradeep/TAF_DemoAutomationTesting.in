package exception;

public class UnsupportedBrowserException extends Exception {

    public UnsupportedBrowserException() {
    }

    public UnsupportedBrowserException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("Unsupported Browser : %s",this.getMessage());
    }
}
