package hr.primefaces.dao;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieReview;

import java.util.List;

public interface IUserMovieReviewDAO {

	void addUserMovieReview(UserMovieReview p_userMovieReview);

	void updateUserMovieReview(UserMovieReview p_userMovieReview);

	void deleteUserMovieReview(UserMovieReview p_userMovieReview);

	List<UserMovieReview> getUserMovieReviewByUserAndMovie(User p_user, Movie p_movie);

	List<UserMovieReview> getAllMovieReviews(Movie p_movie);

	List<UserMovieReview> getUserMovieReviewByUser(User p_user);

}
