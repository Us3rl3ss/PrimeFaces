package hr.primefaces.dao;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieReview;

import java.util.List;

public interface IUserMovieReviewDAO {

	public void addUserMovieReview(UserMovieReview userMovieReview);

	public void updateUserMovieReview(UserMovieReview userMovieReview);

	public void deleteUserMovieReview(UserMovieReview userMovieReview);
	
	public List<UserMovieReview> getUserMovieReviewByUserAndMovie(User user, Movie movie);
	
	public List<UserMovieReview> getAllMovieReviews(Movie movie);
	
	public List<UserMovieReview> getUserMovieReviewByUser(User user);
}
