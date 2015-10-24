package hr.primefaces.dao;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieRate;

import java.util.List;

public interface IUserMovieRateDAO {

	void addUserMovieRate(UserMovieRate p_userMovieRate);

	void updateUserMovieRate(UserMovieRate p_userMovieRate);

	void deleteUserMovieRate(UserMovieRate p_userMovieRate);

	List<UserMovieRate> getUserMovieRateByUserAndMovie(User p_user, Movie p_movie);

	Double getAverageRateByMovie(Movie p_movie);

	List<UserMovieRate> getUserMovieRateByUser(User p_user);

}
