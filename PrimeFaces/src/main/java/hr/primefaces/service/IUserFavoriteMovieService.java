package hr.primefaces.service;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;

import java.util.List;

public interface IUserFavoriteMovieService {

	public void addUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie);

	public void updateUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie);

	public void deleteUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie);

	public List<UserFavoriteMovie> getMovieInUserFavorites(User user, Movie movie);

	public List<UserFavoriteMovie> getUserFavoriteMovieByUser(User user);
}
