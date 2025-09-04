package beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named
@RequestScoped
public class AddMarkersView implements Serializable {
    private static final long serialVersionUID = 1L;

    private MapModel emptyModel;
    private String title;
    private double lat;
    private double lng;

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }

    // Getter/Setter wie gehabt…

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Marker Added", "Lat: " + lat + ", Lng: " + lng));
        resetFields();
    }

    private void resetFields() {
        title = "";
        lat = 0.0;
        lng = 0.0;
    }
}
