package Containers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.stream.Stream;

@ManagedBean(eager = true)
@SessionScoped
public class RadiusButtonsBean extends CommandButtonsBean implements Serializable {
    public RadiusButtonsBean() {
        super(Stream.of(
                        new AbstractMap.SimpleEntry<>(1f, false),
                        new AbstractMap.SimpleEntry<>(2f, false),
                        new AbstractMap.SimpleEntry<>(3f, false),
                        new AbstractMap.SimpleEntry<>(4f, false),
                        new AbstractMap.SimpleEntry<>(5f, false))
                .collect(HashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        HashMap::putAll));
    }
}
