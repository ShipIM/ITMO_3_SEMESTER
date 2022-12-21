package WebApp.Functional;

import java.util.Optional;

public class CachedResult implements Result {
    private final Result result;
    private ResultVariations cache;

    public CachedResult(Result result) {
        this.result = result;
    }

    @Override
    public ResultVariations result() {
        return Optional.ofNullable(cache).orElseGet(() -> {
            this.cache = result.result();

            return this.cache;
        });
    }
}
