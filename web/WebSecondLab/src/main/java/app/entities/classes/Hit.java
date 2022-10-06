package app.entities.classes;

import app.entities.exceptions.WrongInputException;
import app.entities.interfaces.Containable;
import app.entities.interfaces.Resultable;

public class Hit implements Resultable {
    private final Containable<Float> x;
    private final Containable<Float> y;
    private final Containable<Float> r;

    public Hit(Containable<Float> x, Containable<Float> y, Containable<Float> r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public Result result() {
        boolean result;

        try {
            if (x.content() >= 0) {
                result = y.content() > 0 ? firstQuarter() : fourthQuarter();
            } else {
                result = y.content() > 0 ? secondQuarter() : thirdQuarter();
            }
        } catch (WrongInputException e) {
            return Result.ERROR;
        }

        return result ? Result.TRUE : Result.FALSE;
    }

    private boolean firstQuarter() throws WrongInputException {
        return Math.sqrt(Math.pow(x.content(), 2) + Math.pow(y.content(), 2)) <= r.content() / 2;
    }

    private boolean secondQuarter() {
        return false;
    }

    private boolean thirdQuarter() throws WrongInputException {
        return y.content() >= (-0.5 * x.content() - r.content() / 2);
    }

    private boolean fourthQuarter() throws WrongInputException {
        return (Math.abs(x.content()) <= r.content() && Math.abs(y.content()) <= r.content());
    }
}
