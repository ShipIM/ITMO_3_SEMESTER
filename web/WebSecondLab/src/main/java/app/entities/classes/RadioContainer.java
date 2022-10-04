package app.entities.classes;

import app.entities.exceptions.WrongInputException;
import app.entities.interfaces.Containable;

import java.util.List;

public class RadioContainer implements Containable<Float> {
    private final Containable<Float> containable;
    private final List<Float> options;

    public RadioContainer(Containable<Float> containable, List<Float> options) {
        this.containable = containable;
        this.options = options;
    }

    @Override
    public Float content() throws WrongInputException {
        float content = containable.content();

        if (options.contains(content)) {
            return content;
        }

        throw new WrongInputException("There is no such option");
    }
}
