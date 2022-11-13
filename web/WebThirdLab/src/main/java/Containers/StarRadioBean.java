package Containers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.stream.Stream;

@ManagedBean
@SessionScoped
public class StarRadioBean extends RadioButtonsBean<Integer> {
    public StarRadioBean() {
        super(Stream.of(
                        new AbstractMap.SimpleEntry<>("1", 1),
                        new AbstractMap.SimpleEntry<>("2", 2),
                        new AbstractMap.SimpleEntry<>("3", 3),
                        new AbstractMap.SimpleEntry<>("4", 4),
                        new AbstractMap.SimpleEntry<>("5", 5))
                .collect(HashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        HashMap::putAll));
    }
}
