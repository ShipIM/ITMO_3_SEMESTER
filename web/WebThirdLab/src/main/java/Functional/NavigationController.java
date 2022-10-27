package Functional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class NavigationController implements Serializable {
    public NavigationController() {

    }

    public String processStart() {
        return "start";
    }

    public String processMain() {
        return "main";
    }
}