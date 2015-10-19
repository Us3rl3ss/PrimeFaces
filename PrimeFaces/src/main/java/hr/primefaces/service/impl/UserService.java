package hr.primefaces.service.impl;

import hr.primefaces.dao.IUserDAO;
import hr.primefaces.dao.IUserFavoriteMovieDAO;
import hr.primefaces.dao.IUserFollowingDAO;
import hr.primefaces.dao.IUserMovieRateDAO;
import hr.primefaces.dao.IUserMovieReviewDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserFollowing;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "UserService")
@ApplicationScoped
@Transactional(readOnly = true)
public class UserService implements IUserService, Serializable  {

	private static final long serialVersionUID = 1L;
	
	IUserDAO userDAO;
	IUserMovieReviewDAO userMovieReviewDAO;
	IUserMovieRateDAO userMovieRateDAO;
	IUserFollowingDAO userFollowingDAO;
	IUserFavoriteMovieDAO userFavoriteMovieDAO;

	
	/**
	 * ################# USER #################
	 */
	
	@Transactional(readOnly = false)
	@Override
	public void addUser(User user) {
		getUserDAO().addUser(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUser(User user) {
		getUserDAO().deleteUser(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUser(User user) {
		getUserDAO().updateUser(user);
	}

	@Override
	public User getUserById(int id) {
		return getUserDAO().getUserById(id);
	}

	@Override
	public User getUserByDistinctUsername(String username) {
		return getUserDAO().getUserByDistinctUsername(username);
	}
	
	@Override
	public List<User> getUserByUsername(String username) {
		return getUserDAO().getUserByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		return getUserDAO().getUsers();
	}

	@Override
	public List<User> getUserFollow(User loginUser, User currUser) {
		return getUserDAO().getUserFollow(loginUser, currUser);
	}

	@Override
	public List<User> getUserFollowByUser(User loginUser) {
		return getUserDAO().getUserFollowByUser(loginUser);
	}
	
	@Override
	public List<User> getUserFollowByFollower(User currUser) {
		return getUserDAO().getUserFollowByFollower(currUser);
	}
	
	/**
	 * ################# END OF - USER #################
	 */
	
	
	/**
	 * ################# USER MOVIE REVIEW #################
	 */
	
	@Transactional(readOnly = false)
	@Override
	public void addUserMovieReview(UserMovieReview userMovieReview) {
		getUserMovieReviewDAO().addUserMovieReview(userMovieReview);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserMovieReview(UserMovieReview userMovieReview) {
		getUserMovieReviewDAO().deleteUserMovieReview(userMovieReview);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserMovieReview(UserMovieReview userMovieReview) {
		getUserMovieReviewDAO().updateUserMovieReview(userMovieReview);
	}

	public List<UserMovieReview> getUserMovieReviewByUserAndMovie(User user, Movie movie) {
		return getUserMovieReviewDAO().getUserMovieReviewByUserAndMovie(user, movie);
	}

	public List<UserMovieReview> getAllMovieReviews(Movie movie) {
		return getUserMovieReviewDAO().getAllMovieReviews(movie);
	}
	
	public List<UserMovieReview> getUserMovieReviewByUser(User user) {
		return getUserMovieReviewDAO().getUserMovieReviewByUser(user);
	}
	
	/**
	 * ################# END OF - USER MOVIE REVIEW #################
	 */
	
	/**
	 * ################# USER MOVIE RATE #################
	 */
	
	@Transactional(readOnly = false)
	@Override
	public void addUserMovieRate(UserMovieRate userMovieRate) {
		getUserMovieRateDAO().addUserMovieRate(userMovieRate);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserMovieRate(UserMovieRate userMovieRate) {
		getUserMovieRateDAO().deleteUserMovieRate(userMovieRate);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserMovieRate(UserMovieRate userMovieRate) {
		getUserMovieRateDAO().updateUserMovieRate(userMovieRate);
	}

	public List<UserMovieRate> getUserMovieRateByUserAndMovie(User user, Movie movie) {
		return getUserMovieRateDAO().getUserMovieRateByUserAndMovie(user, movie);
	}

	public Double getAverageRateByMovie(Movie movie) {
		return getUserMovieRateDAO().getAverageRateByMovie(movie);
	}
	
	public List<UserMovieRate> getUserMovieRateByUser(User user) {
		return getUserMovieRateDAO().getUserMovieRateByUser(user);
	}
	
	/**
	 * ################# END OF - USER MOVIE RATE #################
	 */
	
	/**
	 * ################# USER FOLLOWING #################
	 */
	
	@Transactional(readOnly = false)
	@Override
	public void addUserFollowing(UserFollowing userFollowing) {
		getUserFollowingDAO().addUserFollowing(userFollowing);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserFollowing(UserFollowing userFollowing) {
		getUserFollowingDAO().deleteUserFollowing(userFollowing);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserFollowing(UserFollowing userFollowing) {
		getUserFollowingDAO().updateUserFollowing(userFollowing);
	}
	
	@Override
	public UserFollowing getUserFriends(User user, User user2) {
		return getUserFollowingDAO().getUserFriends(user, user2);
	}
	
	/**
	 * ################# END OF - USER FOLLOWING #################
	 */
	
	/**
	 * ################# USER FAVORITE MOVIE #################
	 */
	
	@Transactional(readOnly = false)
	@Override
	public void addUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getUserFavoriteMovieDAO().addUserFavoriteMovie(userFavoriteMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getUserFavoriteMovieDAO().deleteUserFavoriteMovie(userFavoriteMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getUserFavoriteMovieDAO().updateUserFavoriteMovie(userFavoriteMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public UserFavoriteMovie getMovieInUserFavorites(User user, Movie movie) {
		return getUserFavoriteMovieDAO().getMovieInUserFavorites(user, movie);
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<UserFavoriteMovie> getUserFavoriteMovieByUser(User user) {
		return getUserFavoriteMovieDAO().getUserFavoriteMovieByUser(user);
	}

	/**
	 * ################# END OF - USER FAVORITE MOVIE #################
	 */
	
	/**
	 * ################# GETTERS AND SETTERS #################
	 */
	
	/**
	 * @return the userDAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @return the userMovieReviewDAO
	 */
	public IUserMovieReviewDAO getUserMovieReviewDAO() {
		return userMovieReviewDAO;
	}

	/**
	 * @return the userMovieRateDAO
	 */
	public IUserMovieRateDAO getUserMovieRateDAO() {
		return userMovieRateDAO;
	}

	/**
	 * @return the userFollowingDAO
	 */
	public IUserFollowingDAO getUserFollowingDAO() {
		return userFollowingDAO;
	}

	/**
	 * @return the userFavoriteMovieDAO
	 */
	public IUserFavoriteMovieDAO getUserFavoriteMovieDAO() {
		return userFavoriteMovieDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @param userMovieReviewDAO the userMovieReviewDAO to set
	 */
	public void setUserMovieReviewDAO(IUserMovieReviewDAO userMovieReviewDAO) {
		this.userMovieReviewDAO = userMovieReviewDAO;
	}

	/**
	 * @param userMovieRateDAO the userMovieRateDAO to set
	 */
	public void setUserMovieRateDAO(IUserMovieRateDAO userMovieRateDAO) {
		this.userMovieRateDAO = userMovieRateDAO;
	}

	/**
	 * @param userFollowingDAO the userFollowingDAO to set
	 */
	public void setUserFollowingDAO(IUserFollowingDAO userFollowingDAO) {
		this.userFollowingDAO = userFollowingDAO;
	}

	/**
	 * @param userFavoriteMovieDAO the userFavoriteMovieDAO to set
	 */
	public void setUserFavoriteMovieDAO(IUserFavoriteMovieDAO userFavoriteMovieDAO) {
		this.userFavoriteMovieDAO = userFavoriteMovieDAO;
	}
}
