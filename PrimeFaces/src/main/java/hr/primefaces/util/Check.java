package hr.primefaces.util;

public class Check {
	
	/**
	 * isNullOrEmpthy
	 */
	public static boolean isNullOrEmpthy(String temp) {

		if (temp != null) {
			
			if (temp.equals("")) {
				
				return true;
			}
			else {

				return false;
			}
		} 
		else {
			
			return true;
		}
	}

}
