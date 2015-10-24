package hr.primefaces.helper;

import hr.primefaces.model.Movie;

public class MovieFavorite {

	private Movie movie;
	private int favoriteNumber;

	public MovieFavorite(final Movie p_movie, final int p_favoriteNumber) {
		this.movie = p_movie;
		this.favoriteNumber = p_favoriteNumber;
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @return the favoriteNumber
	 */
	public int getFavoriteNumber() {
		return favoriteNumber;
	}

	/**
	 * @param p_movie the movie to set
	 */
	public void setMovie(final Movie p_movie) {
		this.movie = p_movie;
	}

	/**
	 * @param p_favoriteNumber the favoriteNumber to set
	 */
	public void setFavoriteNumber(final int p_favoriteNumber) {
		this.favoriteNumber = p_favoriteNumber;
	}

}
