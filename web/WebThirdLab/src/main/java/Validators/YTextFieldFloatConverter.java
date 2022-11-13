package Validators;

import javax.faces.convert.FacesConverter;

@FacesConverter("yTextFieldFloatConverter")
public class YTextFieldFloatConverter extends TextFieldFloatConverter {
    public YTextFieldFloatConverter() {
        super(-4, 4);
    }
}
