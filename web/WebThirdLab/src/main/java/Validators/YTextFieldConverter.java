package Validators;

import javax.faces.convert.FacesConverter;

@FacesConverter("yTextFieldConverter")
public class YTextFieldConverter extends TextFieldConverter {
    public YTextFieldConverter() {
        super(-4, 4);
    }
}
