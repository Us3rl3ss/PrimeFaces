package hr.primefaces.dao;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieRate;

import java.util.List;

public interface IUserMovieRateDAO {

	public void addUserMovieRate(UserMovieRate userMovieRate);

	public void updateUserMovieRate(UserMovieRate userMovieRate);

	public void deleteUserMovieRate(UserMovieRate userMovieRate);
	
	public List<UserMovieRate> getUserMovieRateByUserAndMovie(User user, Movie movie);
	
	public Double getAverageRateByMovie(Movie movie);
	
	public List<UserMovieRate> getUserMovieRateByUser(User user);
}
