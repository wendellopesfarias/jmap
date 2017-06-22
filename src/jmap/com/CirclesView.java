package jmap.com;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
  
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Circle;
  
@ManagedBean(name = "circlesView")
@ViewScoped
public class CirclesView implements Serializable {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MapModel mapModel;
	
	private Circle circle;
	private Marker marker;
      
    @PostConstruct
    public void init() {
         
        mapModel = new DefaultMapModel();
  
        //Canada 740 Notre-Dame Street W.Suite 1260 Montreal, QC H3C 3X6 +1 866-967-1211
        LatLng coord1 = new LatLng(45.4993173,-73.5629035);
        //USA One Boston Place, Suite 2600 Boston, MA 02108 +1 617-933-7265
        LatLng coord2 = new LatLng(42.3548507,-71.1012245);
        //Chile Avenida Apoquindo 4501, Piso 12 Las Condes, Santiago +56 2 2595-2802
        LatLng coord3 = new LatLng(-33.4137836,-70.5853812);
        //My home 8130 Boulevard Pelletier
        LatLng coord4 = new LatLng(45.452111,-73.481585);
 
        //Circle
        Circle circle1 = new Circle(coord1, 500);
        circle1.setStrokeColor("#d93c3c");
        circle1.setFillColor("#d93c3c");
        circle1.setFillOpacity(0.5);
        circle1.setData("Radius 500 metros");
 
        Circle circle2 = new Circle(coord4, 300);
        circle2.setStrokeColor("#00ff00");
        circle2.setFillColor("#00ff00");
        circle2.setStrokeOpacity(0.5);
        circle2.setFillOpacity(0.5);
        circle2.setData("My home 8130 Boulevard Pelletier");
        
        Marker marker = new Marker(coord1);
        marker.setData("Canada");
 
        mapModel.addOverlay(circle1);
        mapModel.addOverlay(marker);
        //mapModel.addOverlay(new Marker(coord1, "Canada 740 Notre-Dame Street W.Suite 1260 Montreal, QC H3C 3X6 +1 866-967-1211"));
        
        mapModel.addOverlay(circle2);
    }
  
    public MapModel getMapModel() {
        return mapModel;
    }
  
    public void onCircleSelect(OverlaySelectEvent event) {
    	
    	System.out.println(event.getOverlay().getClass());
    	
    	String info = "";
    	
    	if(event.getOverlay().getClass() == Circle.class){
    		circle = (Circle) event.getOverlay();
    		info = circle.getData()+" R="+ circle.getRadius();
    		System.out.println(info);
    	}else if(event.getOverlay().getClass() == Marker.class){
    		marker = (Marker) event.getOverlay();
    		info = marker.getTitle() + " data = "+ marker.getData();
    		System.out.println(info);
    	}
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected"+info, info));
 
    }

	public Circle getCircle() {
		return circle;
	}
	
	public Marker getMarker(){
		return marker;
	}
    
   
}