package Entities;

public class Hit implements Resultable<Boolean> {
    private final Containable<Float> x;
    private final Containable<Float> y;
    private final Containable<Float> r;

    public Hit(Containable<Float> x, Containable<Float> y, Containable<Float> r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public Boolean result() {
        boolean result;

        if (x.content() >= 0) {
            result = y.content() >= 0 ? firstQuarter() : fourthQuarter();
        } else {
            result = y.content() >= 0 ? secondQuarter() : thirdQuarter();
        }

        return result;
    }

    private boolean firstQuarter() {
        return Math.sqrt(Math.pow(x.content(), 2) + Math.pow(y.content(), 2)) <= r.content();
    }

    private boolean secondQuarter() {
        return (Math.abs(x.content()) <= (r.content() / 2) && Math.abs(y.content()) <= r.content());
    }

    private boolean thirdQuarter() {
        return false;
    }

    private boolean fourthQuarter() {
        return y.content() >= (2 * x.content() - r.content());
    }
}
