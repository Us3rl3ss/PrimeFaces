package hr.primefaces.dao;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;

import java.util.List;

public interface IUserFavoriteMovieDAO {

	void addUserFavoriteMovie(UserFavoriteMovie p_userFavoriteMovie);

	void updateUserFavoriteMovie(UserFavoriteMovie p_userFavoriteMovie);

	void deleteUserFavoriteMovie(UserFavoriteMovie p_userFavoriteMovie);

	UserFavoriteMovie getMovieInUserFavorites(User p_user, Movie p_movie);

	List<UserFavoriteMovie> getUserFavoriteMovieByUser(User p_user);

}
