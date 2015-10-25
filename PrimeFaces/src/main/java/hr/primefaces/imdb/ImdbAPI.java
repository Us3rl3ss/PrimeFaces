package hr.primefaces.imdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class ImdbAPI {

	private ImdbAPI() {}

	private static final String ACTIVE = "1";
	private static final String NON_ACTIVE = "0";
	private static final String FORMAT = "JSON";
	private static final String LIMIT = "5";
	private static final String LANGUAGE = "en-us";

	/**
	 * getMovieFromImdbById
	 * @param p_movieId
	 * @return
	 */
	public static ImdbJsonModel getMovieFromImdbById(final String p_movieId) {

		ImdbJsonModel result = new ImdbJsonModel();
		HttpURLConnection conn = null;

		try {
			final StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append("http://www.myapifilms.com/imdb");
			urlBuilder.append("?idIMDB=").append(p_movieId);
			urlBuilder.append("&format=").append(FORMAT);
			urlBuilder.append("&aka=").append(NON_ACTIVE);
			urlBuilder.append("&business=").append(NON_ACTIVE);
			urlBuilder.append("&seasons=").append(NON_ACTIVE);
			urlBuilder.append("&seasonYear=").append(NON_ACTIVE);
			urlBuilder.append("&technical=").append(NON_ACTIVE);
			urlBuilder.append("&filter=").append("M");
			urlBuilder.append("&exactFilter=").append(NON_ACTIVE);
			urlBuilder.append("&forceYear=").append(NON_ACTIVE);
			urlBuilder.append("&lang=").append(LANGUAGE);
			urlBuilder.append("&actors=").append("S");
			urlBuilder.append("&biography=").append(NON_ACTIVE);
			urlBuilder.append("&trailer=").append(ACTIVE);
			urlBuilder.append("&uniqueName=").append(NON_ACTIVE);
			urlBuilder.append("&filmography=").append(NON_ACTIVE);
			urlBuilder.append("&bornDied=").append(NON_ACTIVE);
			urlBuilder.append("&starSign=").append(NON_ACTIVE);
			urlBuilder.append("&actorActress=").append(NON_ACTIVE);
			urlBuilder.append("&actorTrivia=").append(NON_ACTIVE);
			urlBuilder.append("&movieTrivia=").append(NON_ACTIVE);
			urlBuilder.append("&awards=").append(NON_ACTIVE);
			urlBuilder.append("&moviePhotos=").append("N");
			urlBuilder.append("&movieVideos=").append("N");
			urlBuilder.append("&similarMovies=").append(ACTIVE);
			urlBuilder.append("&adultSearch=").append(NON_ACTIVE);

			final URL url = new URL(urlBuilder.toString());

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

			result = new Gson().fromJson(full, ImdbJsonModel.class);
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

	/**
	 * getMovieFromImdbByName
	 * @param p_movieName
	 * @return
	 */
	public static List<ImdbJsonModel> getMovieFromImdbByName(final String p_movieName) {

		List<ImdbJsonModel> result = new ArrayList<ImdbJsonModel>();
		HttpURLConnection conn = null;

		try {
			final StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append("http://www.myapifilms.com/imdb");
			urlBuilder.append("?title=").append(p_movieName);
			urlBuilder.append("&format=").append(FORMAT);
			urlBuilder.append("&aka=").append(NON_ACTIVE);
			urlBuilder.append("&business=").append(NON_ACTIVE);
			urlBuilder.append("&seasons=").append(NON_ACTIVE);
			urlBuilder.append("&seasonYear=").append(NON_ACTIVE);
			urlBuilder.append("&technical=").append(NON_ACTIVE);
			urlBuilder.append("&filter=").append("M");
			urlBuilder.append("&exactFilter=").append(NON_ACTIVE);
			urlBuilder.append("&limit=").append(LIMIT);
			urlBuilder.append("&forceYear=").append(NON_ACTIVE);
			urlBuilder.append("&lang=").append(LANGUAGE);
			urlBuilder.append("&actors=").append("N");
			urlBuilder.append("&biography=").append(NON_ACTIVE);
			urlBuilder.append("&trailer=").append(NON_ACTIVE);
			urlBuilder.append("&uniqueName=").append(NON_ACTIVE);
			urlBuilder.append("&filmography=").append(NON_ACTIVE);
			urlBuilder.append("&bornDied=").append(NON_ACTIVE);
			urlBuilder.append("&starSign=").append(NON_ACTIVE);
			urlBuilder.append("&actorActress=").append(NON_ACTIVE);
			urlBuilder.append("&actorTrivia=").append(NON_ACTIVE);
			urlBuilder.append("&movieTrivia=").append(NON_ACTIVE);
			urlBuilder.append("&awards=").append(NON_ACTIVE);
			urlBuilder.append("&moviePhotos=").append("N");
			urlBuilder.append("&movieVideos=").append("N");
			urlBuilder.append("&similarMovies=").append(NON_ACTIVE);
			urlBuilder.append("&adultSearch=").append(NON_ACTIVE);

			final URL url = new URL(urlBuilder.toString());

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

			final Type collectionType = new TypeToken<Collection<ImdbJsonModel>>(){}.getType();
			result = new Gson().fromJson(full, collectionType);
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