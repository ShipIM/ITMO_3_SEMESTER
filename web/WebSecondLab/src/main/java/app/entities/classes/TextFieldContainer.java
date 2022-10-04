package app.entities.classes;

import app.entities.exceptions.WrongInputException;
import app.entities.interfaces.Containable;

public class TextFieldContainer implements Containable<Float> {
    private final Containable<Float> containable;
    private final float lowerBound;
    private final float upperBound;

    public TextFieldContainer(Containable<Float> containable, float lowerBound, float upperBound) {
        this.containable = containable;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Float content() throws WrongInputException {
        float content = containable.content();

        if (content >= lowerBound && content <= upperBound) {
            return content;
        }

        throw new WrongInputException("Out of correct range");
    }
}
