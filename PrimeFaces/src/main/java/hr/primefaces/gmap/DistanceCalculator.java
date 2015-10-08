package hr.primefaces.gmap;

public class DistanceCalculator {

	public DistanceCalculator() {
	}

	/**
	 * distance
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @param unit M-Miles;K-Kilometers;N-Nautical Miles
	 * @return
	 */
	public static Double distance(Location loc1, Location loc2, String unit) {

		Double lon1 = loc1.getLng();
		Double lat1 = loc1.getLat();

		Double lon2 = loc2.getLng();
		Double lat2 = loc2.getLat();

		Double theta = lon1 - lon2;
		Double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	/**
	 * deg2rad decimal degrees to radians
	 * 
	 * @param deg
	 * @return
	 */
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/**
	 * rad2deg radians to decimal degrees
	 * 
	 * @param rad
	 * @return
	 */
	public static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}