package exception;

public class UnsupportedBrowserException extends Exception {

    public UnsupportedBrowserException() {
    }

    public UnsupportedBrowserException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UnsupportedBrowserException : "+this.getMessage()+" browser is not supported";
    }
}
