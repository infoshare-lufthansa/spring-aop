package pl.infoshare.aop.broken;

import org.springframework.stereotype.Component;
import pl.infoshare.aop.retry.Retry;

@Component
public class BrokenService {

    private final static int NUMBER_OF_EXCEPTIONS = 12;
    private int numberOfCalls = 0;

    @Retry(value = BrokenException.class, maxAttempts = 15, delay = 1000)
    public void breakCode() {
        numberOfCalls++;
        if (numberOfCalls < NUMBER_OF_EXCEPTIONS) {
            throw new BrokenException();
        }
    }
}
