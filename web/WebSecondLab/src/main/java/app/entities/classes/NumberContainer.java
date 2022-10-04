package app.entities.classes;

import app.entities.exceptions.WrongInputException;
import app.entities.interfaces.Containable;

public class NumberContainer implements Containable<Float> {
    private final String content;

    public NumberContainer(String content) {
        this.content = content;
    }

    @Override
    public Float content() throws WrongInputException {
        try {
            return Float.parseFloat(content);
        } catch (NumberFormatException | NullPointerException e) {
            throw new WrongInputException("Not a number");
        }
    }
}
