package hr.primefaces.gmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.httpclient.util.URIUtil;

import com.google.gson.Gson;

public final class LocationCalculator {

	private LocationCalculator() {}

	/**
	 * getLocationFromAddress
	 * @param p_address
	 * @return
	 */
	public static Result getLocationFromAddress(final String p_address) {

		Result result = new Result();
		HttpURLConnection conn = null;

		try {
			final URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + URIUtil.encodeQuery(p_address) + "&sensor=true");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String output = "";
			String full = "";
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				full += output;
			}

			final GMapJsonModel gson = new Gson().fromJson(full, GMapJsonModel.class);
			final List<Result> resultList = gson.getResults();

			if (resultList.size() > 0) {

				result = resultList.get(0);
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			conn.disconnect();
		}

		return result;
	}

}