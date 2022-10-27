package Entities;

import java.util.Optional;

public class CachedResultable<T> implements Resultable<T> {
    private final Resultable<T> resultable;
    private T cache;

    public CachedResultable(Resultable<T> resultable) {
        this.resultable = resultable;
    }

    @Override
    public T result() {
        return Optional.ofNullable(cache).orElseGet(() -> {
            this.cache = resultable.result();

            return this.cache;
        });
    }
}
