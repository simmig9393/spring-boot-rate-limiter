@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimited {
    int capacity() default 10;        // tokens
    int refillTokens() default 10;
    int refillSeconds() default 60;   // refill window
}
