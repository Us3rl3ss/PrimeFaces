package hr.primefaces.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	public static final String dd_mm_yyyy = "dd.MM.yyyy";
	public static final String HH_mm = "HH:mm";

	public static void main(String[] args) {
		System.out.println(covertDateToString(new Date(), HH_mm));
	}

	public static String covertDateToString(Date date, String format) {

		String result = "";
		
		if (date != null) {
			
			DateFormat df = new SimpleDateFormat(format);
			result = df.format(date);
		}

		return result;
	}

}
