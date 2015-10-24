package hr.primefaces.gmap;

public final class DistanceCalculator {

	private DistanceCalculator() {}

	/**
	 * distance
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @param p_unit M-Miles;K-Kilometers;N-Nautical Miles
	 * @return
	 */
	public static Double distance(final Location p_loc1, final Location p_loc2, final String p_unit) {

		final Double lon1 = p_loc1.getLng();
		final Double lat1 = p_loc1.getLat();
		final Double lon2 = p_loc2.getLng();
		final Double lat2 = p_loc2.getLat();
		final Double theta = lon1 - lon2;

		Double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		if ("K".equals(p_unit)) {
			dist = dist * 1.609344;
		}
		else if ("N".equals(p_unit)) {
			dist = dist * 0.8684;
		}

		return dist;
	}

	/**
	 * deg2rad decimal degrees to radians
	 * @param p_deg
	 * @return
	 */
	public static double deg2rad(final double p_deg) {
		return p_deg * Math.PI / 180.0;
	}

	/**
	 * rad2deg radians to decimal degrees
	 * @param p_rad
	 * @return
	 */
	public static double rad2deg(final double p_rad) {
		return p_rad * 180 / Math.PI;
	}

}