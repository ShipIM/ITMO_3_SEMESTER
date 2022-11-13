package Containers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.stream.Stream;

@ManagedBean(eager = true)
@SessionScoped
public class TypeRadioBean extends RadioButtonsBean<String> implements Serializable {
    public TypeRadioBean() {
        super(Stream.of(
                        new AbstractMap.SimpleEntry<>("Звезда", "STAR"),
                        new AbstractMap.SimpleEntry<>("Треугольник", "TRIANGLE"))
                .collect(HashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        HashMap::putAll));
    }

    public boolean isStar() {
        if (this.getChosenValue() == null) return false;

        return this.getChosenValue().equals("STAR");
    }

    public boolean isTriangle() {
        if (this.getChosenValue() == null) return false;

        return this.getChosenValue().equals("TRIANGLE");
    }
}
