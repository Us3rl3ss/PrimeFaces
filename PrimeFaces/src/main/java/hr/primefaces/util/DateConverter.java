package hr.primefaces.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateConverter {

	public static final String dd_MM_yyyy = "dd.MM.yyyy";
	public static final String HH_mm = "HH:mm";

	private DateConverter(){}

	/**
	 * covertDateToString
	 * @param p_date
	 * @param p_format
	 * @return
	 */
	public static String covertDateToString(final Date p_date, final String p_format) {

		String result = null;

		if (p_date != null) {

			final DateFormat df = new SimpleDateFormat(p_format);
			result = df.format(p_date);
		}

		return result;
	}

	public static Date setDateDifference(final Date p_initialDate, final  int p_dayDiff, final int p_minDiff) {

		Date result = null;

		if (p_initialDate != null) {

			final Calendar cal = Calendar.getInstance();

			cal.setTime(p_initialDate);
			cal.add(Calendar.DAY_OF_MONTH, p_dayDiff * (-1));
			cal.add(Calendar.MINUTE, p_minDiff * (-1));

			result = cal.getTime();
		}

		return result;
	}

}
