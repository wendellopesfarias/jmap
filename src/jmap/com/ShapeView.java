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
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.map.Circle;
  
@ManagedBean(name = "shapeView")
@ViewScoped
public class ShapeView implements Serializable {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DashboardModel mapDashboardModel;
	private MapModel mapModel;
	
	private Circle circle;
	private Marker marker;
      
    @PostConstruct
    public void init() {
    	
    	mapDashboardModel = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        
        column1.addWidget("canada");
        column2.addWidget("usa");
        column3.addWidget("chile");
        
        mapDashboardModel.addColumn(column1);
        mapDashboardModel.addColumn(column2);
        mapDashboardModel.addColumn(column3);
         
        mapModel = new DefaultMapModel();
  
        //Canada 740 Notre-Dame Street W.Suite 1260 Montreal, QC H3C 3X6 +1 866-967-1211
        LatLng coord1 = new LatLng(45.4993173,-73.5629035);
        
        double [] latlon = {45.4993000,-73.5629000};
        double[] ll = LatLon.getLatLon(latlon, 10.0, 45.0); 
        //LatLng coord11 = new LatLng(ll[0],ll[1]);
        LatLng coord11 = new LatLng(45.4993173,-73.5629035);
        //USA One Boston Place, Suite 2600 Boston, MA 02108 +1 617-933-7265
        LatLng coord2 = new LatLng(42.3548507,-71.1012245);
        //Chile Avenida Apoquindo 4501, Piso 12 Las Condes, Santiago +56 2 2595-2802
        LatLng coord3 = new LatLng(-33.4137836,-70.5853812);
        //My home 8130 Boulevard Pelletier
        LatLng coord4 = new LatLng(45.452111,-73.481585);
 
        //Circle
        Circle circle1 = new Circle(coord1, 300);
        circle1.setStrokeColor("(49,59,63)");
        circle1.setFillColor(Gradient.COLOR[2]);
        circle1.setFillOpacity(Gradient.INTENSITY[2]);
        circle1.setData("Radius 500 metros");
        
        Circle circle11 = new Circle(coord11, 100);
        circle11.setStrokeColor("(49,59,63)");
        circle11.setFillColor(Gradient.COLOR[13]);
        circle11.setFillOpacity(Gradient.INTENSITY[3]);
        circle11.setData("Radius 500 metros");
 
        Circle circle2 = new Circle(coord4, 300);
        circle2.setStrokeColor("#00ff00");
        circle2.setFillColor("#00ff00");
        circle2.setStrokeOpacity(0.5);
        circle2.setFillOpacity(0.5);
        circle2.setData("My home 8130 Boulevard Pelletier");
        
       // Marker marker = new Marker(coord1);
        //marker.setData("Canada");
 
        mapModel.addOverlay(circle1);
        mapModel.addOverlay(circle11);
        
       // mapModel.addOverlay(marker);
       Marker k2Canada = new Marker(coord1, "Canada 740 Notre-Dame Street W.Suite 1260 Montreal QC H3C 3X6 +1 866-967-1211", 
    		   "k2-logo.png", 
       		   "http://jquine.com/jmap/resources/k2-logo-pin.png");       
       Marker k2USA = new Marker(coord2, "USA One Boston Place, Suite 2600 Boston, MA 02108 +1 617-933-7265", 
    		   "k2-logo.png", 
       		   "http://jquine.com/jmap/resources/k2-logo-pin.png");
       Marker k2Chile = new Marker(coord3, "Chile Avenida Apoquindo 4501, Piso 12 Las Condes, Santiago +56 2 2595-2802", 
    		   "k2-logo.png", 
       		   "http://jquine.com/jmap/resources/k2-logo-pin.png");
       
       
       mapModel.addOverlay(k2Canada);
       mapModel.addOverlay(k2USA);
       mapModel.addOverlay(k2Chile);
        
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
    		info = circle.getData()+" R = "+ circle.getRadius();
    		System.out.println(info);
    	}else if(event.getOverlay().getClass() == Marker.class){
    		marker = (Marker) event.getOverlay();
    		info = marker.getTitle() + " data = "+ marker.getData();
    		System.out.println(info);
    	}
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected"+info, null));
 
    }

	public Circle getCircle() {
		return circle;
	}
	
	public Marker getMarker(){
		return marker;
	}

	public DashboardModel getMapDashboardModel() {
		return mapDashboardModel;
	}
    
   
}