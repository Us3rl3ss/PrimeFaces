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

public class LocationCalculator {

	public LocationCalculator() {
	}
	
	/**
	 * getLocationFromAddress
	 * @param address
	 * @return
	 */
	public static Result getLocationFromAddress(String address) {

		Result result = new Result();

		try {
			URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + URIUtil.encodeQuery(address)
					+ "&sensor=true");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = "", full = "";
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				full += output;
			}

			GMapJsonModel gson = new Gson().fromJson(full, GMapJsonModel.class);
			List<Result> resultList = gson.getResults();

			if (resultList.size() > 0) {

				result = resultList.get(0);

//				location = result.getGeometry().getLocation();
			}
			
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}