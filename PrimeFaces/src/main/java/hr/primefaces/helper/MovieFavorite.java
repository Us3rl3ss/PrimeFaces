package hr.primefaces.helper;

import hr.primefaces.model.Movie;

public class MovieFavorite {

	private Movie movie;
	private int favoriteNumber;
	
	public MovieFavorite(Movie movie, int favoriteNumber) {
		this.movie = movie;
		this.favoriteNumber = favoriteNumber;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getFavoriteNumber() {
		return favoriteNumber;
	}

	public void setFavoriteNumber(int favoriteNumber) {
		this.favoriteNumber = favoriteNumber;
	}

}
