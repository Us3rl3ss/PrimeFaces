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

	void addUser(User p_user);

	void updateUser(User p_user);

	void deleteUser(User p_user);

	User getUserById(int p_id);

	User getUserByDistinctUsername(String p_username);

	List<User> getUsers();

	List<User> getUserByUsername(String p_input);

	List<User> getUserFollow(User p_loginUser, User p_currUser);

	List<User> getUserFollowByUser(User p_loginUser);

	List<User> getUserFollowByFollower(User p_currUser);

	/**
	 * ################# END OF - USER #################
	 */

	void addUserMovieReview(UserMovieReview p_userMovieReview);

	void updateUserMovieReview(UserMovieReview p_userMovieReview);

	void deleteUserMovieReview(UserMovieReview p_userMovieReview);

	List<UserMovieReview> getUserMovieReviewByUserAndMovie(User p_user, Movie p_movie);

	List<UserMovieReview> getAllMovieReviews(Movie p_movie);

	List<UserMovieReview> getUserMovieReviewByUser(User p_user);

	/**
	 * ################# USER MOVIE RATE #################
	 */

	void addUserMovieRate(UserMovieRate p_userMovieRate);

	void updateUserMovieRate(UserMovieRate p_userMovieRate);

	void deleteUserMovieRate(UserMovieRate p_userMovieRate);

	List<UserMovieRate> getUserMovieRateByUserAndMovie(User p_user, Movie p_movie);

	Double getAverageRateByMovie(Movie p_movie);

	List<UserMovieRate> getUserMovieRateByUser(User p_user);

	/**
	 * ################# USER FOLLOWING #################
	 */

	void addUserFollowing(UserFollowing p_userFollowing);

	void updateUserFollowing(UserFollowing p_userFollowing);

	void deleteUserFollowing(UserFollowing p_userFollowing);

	UserFollowing getUserFriends(User p_user, User p_user2);

	/**
	 * ################# USER FAVORITE MOVIE #################
	 */

	void addUserFavoriteMovie(UserFavoriteMovie p_userFavoriteMovie);

	void updateUserFavoriteMovie(UserFavoriteMovie p_userFavoriteMovie);

	void deleteUserFavoriteMovie(UserFavoriteMovie p_userFavoriteMovie);

	UserFavoriteMovie getMovieInUserFavorites(User p_user, Movie p_movie);

	List<UserFavoriteMovie> getUserFavoriteMovieByUser(User p_user);

}
