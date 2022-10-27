package Functional;

import Containers.RadiusButtonsBean;
import Containers.XCoordinateButtonsBean;
import Containers.YInputTextBean;
import DataBase.DataBaseHit;
import DataBase.HitService;
import Entities.CachedResultable;
import Entities.FloatContainer;
import Entities.Hit;
import Entities.Timer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.time.LocalDateTime;

@ManagedBean
@RequestScoped
public class ProcessBean implements Serializable {
    @ManagedProperty(value = "#{radiusButtonsBean}")
    private RadiusButtonsBean r;

    @ManagedProperty(value = "#{xCoordinateButtonsBean}")
    private XCoordinateButtonsBean x;

    @ManagedProperty(value = "#{yInputTextBean}")
    private YInputTextBean y;

    @ManagedProperty(value = "#{hitService}")
    private HitService service;

    public ProcessBean() {

    }

    public void formRequest() {
        FloatContainer r = new FloatContainer(this.r.getValue());
        FloatContainer x = new FloatContainer(this.x.getValue());
        FloatContainer y = new FloatContainer(this.y.getValue());

        Hit hit = new Hit(x, y, r);
        CachedResultable<Boolean> hitCached = new CachedResultable<>(hit);
        Timer timer = new Timer(hitCached);
        LocalDateTime dateTime = LocalDateTime.now();

        service.create(new DataBaseHit(this.r.getValue(), this.x.getValue(), this.y.getValue(), hit.result(),
                dateTime, timer.time()));

        this.setDefault();
    }

    private void setDefault() {
        this.r.setDefault();
        this.x.setDefault();
        this.y.setValue(null);
    }

    public HitService getService() {
        return service;
    }

    public RadiusButtonsBean getR() {
        return r;
    }

    public XCoordinateButtonsBean getX() {
        return x;
    }

    public YInputTextBean getY() {
        return y;
    }

    public void setService(HitService service) {
        this.service = service;
    }

    public void setR(RadiusButtonsBean r) {
        this.r = r;
    }

    public void setX(XCoordinateButtonsBean x) {
        this.x = x;
    }

    public void setY(YInputTextBean y) {
        this.y = y;
    }
}
