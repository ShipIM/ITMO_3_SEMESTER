package app.entities.classes;

import app.entities.interfaces.Resultable;

import java.util.Optional;

public class CachedResultable<T> implements Resultable {
    private final Resultable resultable;
    private Result cache;

    public CachedResultable(Resultable resultable) {
        this.resultable = resultable;
    }

    @Override
    public Result result() {
        return Optional.ofNullable(cache).orElseGet(() -> {
            this.cache = resultable.result();

            return this.cache;
        });
    }
}
