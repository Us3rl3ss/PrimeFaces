package hr.primefaces.service;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserFollowing;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;

import java.util.List;

public interface IUserService {

	/**
	 * ################# USER #################
	 */

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User getUserById(int id);

	public User getUserByDistinctUsername(String username);

	public List<User> getUsers();

	public List<User> getUserByUsername(String input);

	public List<User> getUserFollow(User loginUser, User currUser);

	public List<User> getUserFollowByUser(User loginUser);

	public List<User> getUserFollowByFollower(User currUser);

	/**
	 * ################# END OF - USER #################
	 */

	/**
	 * ################# USER MOVIE REVIEW #################
	 */

	public void addUserMovieReview(UserMovieReview userMovieReview);

	public void updateUserMovieReview(UserMovieReview userMovieReview);

	public void deleteUserMovieReview(UserMovieReview userMovieReview);

	public List<UserMovieReview> getUserMovieReviewByUserAndMovie(User user, Movie movie);

	public List<UserMovieReview> getAllMovieReviews(Movie movie);

	public List<UserMovieReview> getUserMovieReviewByUser(User user);

	/**
	 * ################# END OF - USER MOVIE REVIEW #################
	 */

	/**
	 * ################# USER MOVIE RATE #################
	 * 
	 */

	public void addUserMovieRate(UserMovieRate userMovieRate);

	public void updateUserMovieRate(UserMovieRate userMovieRate);

	public void deleteUserMovieRate(UserMovieRate userMovieRate);

	public List<UserMovieRate> getUserMovieRateByUserAndMovie(User user, Movie movie);

	public Double getAverageRateByMovie(Movie movie);

	public List<UserMovieRate> getUserMovieRateByUser(User user);

	/**
	 * ################# END OF - USER MOVIE RATE #################
	 */

	/**
	 * ################# USER FOLLOWING #################
	 */

	public void addUserFollowing(UserFollowing userFollowing);

	public void updateUserFollowing(UserFollowing userFollowing);

	public void deleteUserFollowing(UserFollowing userFollowing);

	public UserFollowing getUserFriends(User user, User user2);

	/**
	 * ################# END OF - USER FOLLOWING #################
	 */

	/**
	 * ################# USER FAVORITE MOVIE #################
	 */

	public void addUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie);

	public void updateUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie);

	public void deleteUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie);

	public UserFavoriteMovie getMovieInUserFavorites(User user, Movie movie);

	public List<UserFavoriteMovie> getUserFavoriteMovieByUser(User user);

	/**
	 * ################# END OF - USER FAVORITE MOVIE #################
	 */
}
