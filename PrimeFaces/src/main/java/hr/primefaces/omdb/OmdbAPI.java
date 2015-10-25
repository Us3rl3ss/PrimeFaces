package hr.primefaces.omdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.util.URIUtil;

import com.google.gson.Gson;

public final class OmdbAPI {

	private OmdbAPI() {}

	/**
	 * getMovieFromOmdb
	 * @param p_movieName
	 * @return
	 */
	public static OmdbJsonModel getMovieFromOmdb(final String p_movieName) {

		OmdbJsonModel result = new OmdbJsonModel();
		HttpURLConnection conn = null;

		try {
			final URL url = new URL("http://www.omdbapi.com/?t=" + URIUtil.encodeQuery(p_movieName) + "&y=&plot=full&r=json");
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

			result = new Gson().fromJson(full, OmdbJsonModel.class);
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