package WebApp.Functional;

public class Hit implements Result {
    private final ConditionalContainer<Float> x;
    private final ConditionalContainer<Float> y;
    private final ConditionalContainer<Float> r;

    public Hit(ConditionalContainer<Float> x, ConditionalContainer<Float> y, ConditionalContainer<Float> r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public ResultVariations result() {
        boolean result;

        if (x.content() >= 0) {
            result = y.content() > 0 ? firstQuarter() : fourthQuarter();
        } else {
            result = y.content() > 0 ? secondQuarter() : thirdQuarter();
        }

        return result ? ResultVariations.TRUE : ResultVariations.FALSE;
    }

    private boolean firstQuarter() {
        return Math.sqrt(Math.pow(x.content(), 2) + Math.pow(y.content(), 2)) <= r.content();
    }

    private boolean secondQuarter() {
        return false;
    }

    private boolean thirdQuarter() {
        return (Math.abs(x.content()) <= r.content() / 2 && Math.abs(y.content()) <= r.content());
    }

    private boolean fourthQuarter() {
        return y.content() >= (x.content() - r.content() / 2);
    }
}
