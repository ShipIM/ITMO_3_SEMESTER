package Functional;

import Containers.*;
import DataBase.DataBaseStarHit;
import DataBase.DataBaseTriangleHit;
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

    @ManagedProperty(value = "#{typeRadioBean}")
    private TypeRadioBean type;

    @ManagedProperty(value = "#{triangleRadioBean}")
    private TriangleRadioBean triangle;

    @ManagedProperty(value = "#{starRadioBean}")
    private StarRadioBean star;

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

        switch (type.getChosenValue()) {
            case "TRIANGLE": {
                service.create(new DataBaseTriangleHit(this.r.getValue(),
                        this.x.getValue(),
                        this.y.getValue(),
                        hit.result(),
                        dateTime,
                        timer.time(),
                        triangle.getChosenValue()));
                break;
            }
            case "STAR": {
                service.create(new DataBaseStarHit(this.r.getValue(),
                        this.x.getValue(),
                        this.y.getValue(),
                        hit.result(),
                        dateTime,
                        timer.time(),
                        star.getChosenValue()));
            }
        }

        this.setDefault();
    }

    private void setDefault() {
        this.r.setDefault();
        this.x.setDefault();
        this.y.setValue(null);
        this.type.setChosenValue(null);
        this.star.setChosenValue(null);
        this.triangle.setChosenValue(null);
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

    public StarRadioBean getStar() {
        return star;
    }

    public TriangleRadioBean getTriangle() {
        return triangle;
    }

    public TypeRadioBean getType() {
        return type;
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

    public void setStar(StarRadioBean star) {
        this.star = star;
    }

    public void setTriangle(TriangleRadioBean triangle) {
        this.triangle = triangle;
    }

    public void setType(TypeRadioBean type) {
        this.type = type;
    }
}
