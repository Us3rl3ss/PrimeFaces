package hr.primefaces.util;

import hr.primefaces.imdb.Actor;
import hr.primefaces.imdb.Director;
import hr.primefaces.imdb.ImdbJsonModel;
import hr.primefaces.imdb.SimilarMovie;
import hr.primefaces.model.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

public final class ObjectRemapper {

	private static final String DELIM = ", ";

	private ObjectRemapper(){}

	public static Movie imdbToMovieObj(final ImdbJsonModel p_imdbMovie) {

		final Movie result = new Movie();

		result.setName(ObjectRemapper.processTitle(p_imdbMovie.getTitle()));
		result.setYearOfCreation(ObjectRemapper.processYear(p_imdbMovie.getYear()));
		result.setDirectorName(ObjectRemapper.processDirector(p_imdbMovie.getDirectors()));
		result.setDuration(ObjectRemapper.processRuntime(p_imdbMovie.getRuntime()));
		result.setInfo(ObjectRemapper.processPlot(p_imdbMovie.getSimplePlot()));
		result.setImage(ObjectRemapper.processPoster(p_imdbMovie.getUrlPoster()));
		result.setActors(ObjectRemapper.processActors(p_imdbMovie.getActors()));
		result.setGenres(ObjectRemapper.processGenres(p_imdbMovie.getGenres()));
		result.setImdbId(ObjectRemapper.processImdbId(p_imdbMovie.getIdIMDB()));
		result.setMetascore(ObjectRemapper.processMetascore(p_imdbMovie.getMetascore()));
		result.setImdbRating(ObjectRemapper.processImdbRating(p_imdbMovie.getRating()));
		result.setSimilarMovies(ObjectRemapper.processSimilarMovies(p_imdbMovie.getSimilarMovies()));
		result.setImdbUrl(ObjectRemapper.processImdbUrl(p_imdbMovie.getUrlIMDB()));
		result.setImdbVotes(ObjectRemapper.processImdbVotes(p_imdbMovie.getVotes()));
		result.setImdbPosterUrl(ObjectRemapper.processImdbPosterUrl(p_imdbMovie.getUrlPoster()));

		return result;
	}

	/**
	 * processTitle
	 * @param p_title
	 * @return
	 */
	private static String processTitle(final String p_title) {

		if (Check.isNullOrEmpthy(p_title)) {
			return "-";
		}
		else {
			return p_title;
		}
	}

	/**
	 * processYear
	 * @param p_year (123 min)
	 * @return
	 */
	private static int processYear(final String p_year) {

		if (Check.isNullOrEmpthy(p_year)) {
			return 0;
		}
		else {
			return NumberFormatter.stringToInt(p_year);
		}
	}

	/**
	 * processDirector
	 * @param p_directors
	 * @return
	 */
	private static String processDirector(final List<Director> p_directors) {

		if (Check.isNullOrEmpthy(p_directors)) {

			return "-";
		}
		else {

			final StringBuilder sb = new StringBuilder();

			String delim = "";
			for(Director d: p_directors) {

				if (!Check.isNullOrEmpthy(d.getName())) {
					sb.append(delim).append(d.getName());
					delim = DELIM;
				}
			}

			return sb.toString();
		}
	}

	/**
	 * processRuntime
	 * @param p_runtime
	 * @return
	 */
	private static int processRuntime(final List<String> p_runtime) {

		if (Check.isNullOrEmpthy(p_runtime)) {
			return 0;
		}
		else {
			final String run = p_runtime.get(0);
			return NumberFormatter.stringToInt(run.replaceAll("[^\\d]", ""));
		}
	}

	/**
	 * processPlot
	 * @param p_simplePlot
	 * @return
	 */
	private static String processPlot(final String p_simplePlot) {

		if (Check.isNullOrEmpthy(p_simplePlot)) {
			return "-";
		}
		else {
			return p_simplePlot;
		}
	}

	/**
	 * processPoster
	 * @param p_urlPoster
	 * @return
	 */
	private static byte[] processPoster(final String p_urlPoster) {

		byte[] result = null;

		if (Check.isNullOrEmpthy(p_urlPoster)) {
			return result;
		}
		else {

			URL url;
			try {
				url = new URL(p_urlPoster);
				final InputStream is = url.openStream();
				result = IOUtils.toByteArray(is);
			}
			catch (MalformedURLException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * processActors
	 * @param p_actors
	 * @return
	 */
	private static String processActors(final List<Actor> p_actors) {

		if (Check.isNullOrEmpthy(p_actors)) {

			return "-";
		}
		else {

			final StringBuilder sb = new StringBuilder();

			String delim = "";
			for(Actor a: p_actors) {

				if (!Check.isNullOrEmpthy(a.getActorName())) {
					sb.append(delim).append(a.getActorName());
					delim = DELIM;
				}
			}

			return sb.toString();
		}
	}

	/**
	 * processGenres
	 * @param p_genres
	 * @return
	 */
	private static String processGenres(final List<String> p_genres) {

		if (Check.isNullOrEmpthy(p_genres)) {

			return "-";
		}
		else {

			final StringBuilder sb = new StringBuilder();

			String delim = "";
			for(String s: p_genres) {

				if (!Check.isNullOrEmpthy(s)) {
					sb.append(delim).append(s);
					delim = DELIM;
				}
			}

			return sb.toString();
		}
	}

	/**
	 * processImdbId
	 * @param p_idIMDB
	 * @return
	 */
	private static String processImdbId(final String p_idIMDB) {

		if (Check.isNullOrEmpthy(p_idIMDB)) {
			return null;
		}
		else {
			return p_idIMDB;
		}
	}

	/**
	 * processMetascore
	 * @param p_metascore
	 * @return
	 */
	private static String processMetascore(final String p_metascore) {

		if (Check.isNullOrEmpthy(p_metascore)) {
			return "-";
		}
		else {
			return p_metascore;
		}
	}

	/**
	 * processImdbRating
	 * @param p_rating
	 * @return
	 */
	private static String processImdbRating(final String p_rating) {

		if (Check.isNullOrEmpthy(p_rating)) {
			return "-";
		}
		else {
			return p_rating;
		}
	}

	/**
	 * processSimilarMovies
	 * @param p_similarMovies
	 * @return
	 */
	private static String processSimilarMovies(final List<SimilarMovie> p_similarMovies) {

		if (Check.isNullOrEmpthy(p_similarMovies)) {

			return "-";
		}
		else {

			final StringBuilder sb = new StringBuilder();

			String delim = "";
			for(SimilarMovie sm: p_similarMovies) {

				if (!Check.isNullOrEmpthy(sm.getName())) {
					sb.append(delim).append(sm.getName());
					delim = DELIM;
				}
			}

			return sb.toString();
		}
	}

	/**
	 * processImdbUrl
	 * @param p_urlIMDB
	 * @return
	 */
	private static String processImdbUrl(final String p_urlIMDB) {

		if (Check.isNullOrEmpthy(p_urlIMDB)) {
			return "-";
		}
		else {
			return p_urlIMDB;
		}
	}

	/**
	 * processImdbVotes
	 * @param p_votes
	 * @return
	 */
	private static String processImdbVotes(final String p_votes) {

		if (Check.isNullOrEmpthy(p_votes)) {
			return "-";
		}
		else {
			return p_votes;
		}
	}

	/**
	 * p_urlPoster
	 * @param p_urlPoster
	 * @return
	 */
	private static String processImdbPosterUrl(final String p_urlPoster) {

		if (Check.isNullOrEmpthy(p_urlPoster)) {
			return null;
		}
		else {
			return p_urlPoster;
		}
	}

}
