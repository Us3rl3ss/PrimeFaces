package hr.primefaces.util;

public final class Check {

	private Check(){}

	/**
	 * isNullOrEmpthy
	 * @param p_temp
	 * @return
	 */
	public static boolean isNullOrEmpthy(final String p_temp) {

		if (p_temp != null) {

			return "".equals(p_temp);
		}
		else {

			return true;
		}
	}

}
