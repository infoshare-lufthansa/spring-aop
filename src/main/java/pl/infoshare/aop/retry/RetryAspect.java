package pl.infoshare.aop.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RetryAspect {

    @Around("@annotation(pl.infoshare.aop.retry.Retry)")
    public Object retryCall(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        var retryAnnotation = methodSignature.getMethod().getAnnotation(Retry.class);

        var delay = retryAnnotation.delay();
        var maxAttempts = retryAnnotation.maxAttempts();
        var exception = retryAnnotation.value();

        int attempts = 0;
        while (attempts < maxAttempts) {
            attempts++;
            try {
                System.out.println("Try number " + attempts);
                return joinPoint.proceed();
            } catch (Throwable ex) {
                if (!ex.getClass().isAssignableFrom(exception)) {
                    throw ex;
                }
                if (attempts >= maxAttempts) {
                    throw ex;
                }
                Thread.sleep(delay);
            }
        }

        throw new RuntimeException("You shouldn't be here");
    }
}
