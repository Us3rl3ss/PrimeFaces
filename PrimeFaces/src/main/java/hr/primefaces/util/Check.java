package hr.primefaces.util;

import java.util.Collection;

public final class Check {

	private Check(){}

	/**
	 * isNullOrEmpthy
	 * @param p_temp
	 * @return
	 */
	public static boolean isNullOrEmpthy(final Object p_temp) {

		boolean result = true;

		if (p_temp != null) {

			if (p_temp instanceof String) {

				if (p_temp != null) {

					result = "".equals(p_temp);
				}
			}
			else if (p_temp instanceof Collection<?>) {

				if (p_temp != null) {

					result = 0 == ((Collection<?>) p_temp).size();
				}
			}
		}

		return result;
	}

}
