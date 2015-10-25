package hr.primefaces.util;

public final class NumberFormatter {

	private NumberFormatter(){}

	/**
	 * stringToInt
	 * @param p_temp
	 * @return
	 */
	public static int stringToInt(final String p_temp) {

		int result = 0;

		if (p_temp != null) {

			try {
			      result = Integer.parseInt(p_temp);
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
