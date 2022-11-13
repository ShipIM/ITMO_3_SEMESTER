package Containers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(eager = true)
@SessionScoped
public class YInputTextBean extends TextBean<Float> implements Serializable {
    public YInputTextBean() {

    }

    public YInputTextBean(Float value) {
        super(value);
    }
}
