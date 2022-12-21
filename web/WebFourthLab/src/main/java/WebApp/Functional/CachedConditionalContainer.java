package WebApp.Functional;

public class CachedConditionalContainer<T> implements ConditionalContainer<T> {
    private final ConditionalContainer<T> conditionalContainer;
    private T cache;

    public CachedConditionalContainer(ConditionalContainer<T> conditionalContainer) {
        this.conditionalContainer = conditionalContainer;
    }

    @Override
    public T content() {
        if (cache == null) {
            this.cache = conditionalContainer.content();
        }

        return this.cache;
    }
}
