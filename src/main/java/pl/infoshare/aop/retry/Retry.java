package pl.infoshare.aop.retry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    Class<? extends Exception> value();
    int maxAttempts() default 3;
    int delay() default 300;
}
