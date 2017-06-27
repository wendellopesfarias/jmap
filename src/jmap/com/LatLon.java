package jmap.com;

public class LatLon {
	
	private static final double RADIUS_LATITUDE = 0.0000180;
	private static final double RADIUS_LONGITUDE = 0.0000260;
	private static final double ERR_LATITUDE = 0.0000001;
	private static final double ERR_LONGITUDE = 0.0000002;

	public static void main(String[] args){
	
		double[] latlon = {45.0000000,73.0000000};
		
		double[] ll = getLatLon(latlon, 1.0, 0.0); 		
		System.out.println(ll[0]+" : "+ll[1]);
		
	}
	
	
	public static double[] getLatLon(double[] latlon, double radius, double degrees){
		double cos = Math.cos(Math.toRadians(degrees));
		double sin = Math.sin(Math.toRadians(degrees));
		latlon[0] = radius * RADIUS_LATITUDE * sin + latlon[0];
		latlon[1] = radius * RADIUS_LONGITUDE * cos +latlon[1];
		return latlon;	
	}
	
	
	
}
