package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public abstract class TextFieldFloatConverter implements Converter {
    private float lowerBound;
    private float upperBound;

    public TextFieldFloatConverter() {

    }

    public TextFieldFloatConverter(float lowerBound, float upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Object getAsObject(FacesContext context, UIComponent ui, String content) {
        if (content == null || content.equals("")) throw new ConverterException(
                new FacesMessage("Поле не должно быть пустым."));

        try {
            float number = Float.parseFloat(content);

            if (number < this.lowerBound || number > this.upperBound) {
                throw new ConverterException(
                        new FacesMessage("Число должно быть в диапазоне от " + this.lowerBound
                                + " до " + this.upperBound + "."));
            }

            return number;
        } catch (NumberFormatException e) {
            throw new ConverterException(
                    new FacesMessage("Необходимо ввести число"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent ui, Object object) {
        return object.toString();
    }

    public void setLowerBound(float lowerBound) {
        this.lowerBound = lowerBound;
    }

    public float getLowerBound() {
        return lowerBound;
    }

    public void setUpperBound(float upperBound) {
        this.upperBound = upperBound;
    }

    public float getUpperBound() {
        return upperBound;
    }
}
