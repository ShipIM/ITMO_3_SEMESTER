package app.entities.classes;

import app.entities.exceptions.WrongInputException;
import app.entities.interfaces.Containable;

public class CachedContainable<T> implements Containable<T> {
    private final Containable<T> containable;
    private T cache;

    public CachedContainable(Containable<T> containable) {
        this.containable = containable;
    }

    @Override
    public T content() throws WrongInputException {
        if (cache == null) {
            this.cache = containable.content();
        }

        return this.cache;
    }
}
