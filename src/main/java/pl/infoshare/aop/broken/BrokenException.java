package pl.infoshare.aop.broken;

public class BrokenException extends RuntimeException {
    public BrokenException() {
        super("This exception is broken");
    }
}
