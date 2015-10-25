package hr.primefaces.service.impl;

import hr.primefaces.dao.IUserDAO;
import hr.primefaces.dao.IUserFavoriteMovieDAO;
import hr.primefaces.dao.IUserFollowingDAO;
import hr.primefaces.dao.IUserMovieRateDAO;
import hr.primefaces.dao.IUserMovieReviewDAO;
import hr.primefaces.dao.IUserMovieWishlistDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserFollowing;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.model.UserMovieWishlist;
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

	private IUserDAO userDAO;
	private IUserMovieReviewDAO userMovieReviewDAO;
	private IUserMovieRateDAO userMovieRateDAO;
	private IUserFollowingDAO userFollowingDAO;
	private IUserFavoriteMovieDAO userFavoriteMovieDAO;
	private IUserMovieWishlistDAO userMovieWishlistDAO;

	/**
	 * ################# USER #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addUser(final User p_user) {
		getUserDAO().addUser(p_user);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUser(final User p_user) {
		getUserDAO().deleteUser(p_user);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUser(final User p_user) {
		getUserDAO().updateUser(p_user);
	}

	@Override
	public User getUserById(final int p_id) {
		return getUserDAO().getUserById(p_id);
	}

	@Override
	public User getUserByDistinctUsername(final String p_username) {
		return getUserDAO().getUserByDistinctUsername(p_username);
	}

	@Override
	public List<User> getUserByUsername(final String p_username) {
		return getUserDAO().getUserByUsername(p_username);
	}

	@Override
	public List<User> getUsers() {
		return getUserDAO().getUsers();
	}

	@Override
	public List<User> getUserFollow(final User p_loginUser, final User p_currUser) {
		return getUserDAO().getUserFollow(p_loginUser, p_currUser);
	}

	@Override
	public List<User> getUserFollowByUser(final User p_loginUser) {
		return getUserDAO().getUserFollowByUser(p_loginUser);
	}

	@Override
	public List<User> getUserFollowByFollower(final User p_currUser) {
		return getUserDAO().getUserFollowByFollower(p_currUser);
	}

	/**
	 * ################# USER MOVIE REVIEW #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addUserMovieReview(final UserMovieReview p_userMovieReview) {
		getUserMovieReviewDAO().addUserMovieReview(p_userMovieReview);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserMovieReview(final UserMovieReview p_userMovieReview) {
		getUserMovieReviewDAO().deleteUserMovieReview(p_userMovieReview);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserMovieReview(final UserMovieReview p_userMovieReview) {
		getUserMovieReviewDAO().updateUserMovieReview(p_userMovieReview);
	}

	public List<UserMovieReview> getUserMovieReviewByUserAndMovie(final User p_user, final Movie p_movie) {
		return getUserMovieReviewDAO().getUserMovieReviewByUserAndMovie(p_user, p_movie);
	}

	public List<UserMovieReview> getAllMovieReviews(final Movie p_movie) {
		return getUserMovieReviewDAO().getAllMovieReviews(p_movie);
	}

	public List<UserMovieReview> getUserMovieReviewByUser(final User p_user) {
		return getUserMovieReviewDAO().getUserMovieReviewByUser(p_user);
	}

	/**
	 * ################# USER MOVIE RATE #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addUserMovieRate(final UserMovieRate p_userMovieRate) {
		getUserMovieRateDAO().addUserMovieRate(p_userMovieRate);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserMovieRate(final UserMovieRate p_userMovieRate) {
		getUserMovieRateDAO().deleteUserMovieRate(p_userMovieRate);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserMovieRate(final UserMovieRate p_userMovieRate) {
		getUserMovieRateDAO().updateUserMovieRate(p_userMovieRate);
	}

	public List<UserMovieRate> getUserMovieRateByUserAndMovie(final User p_user, final Movie p_movie) {
		return getUserMovieRateDAO().getUserMovieRateByUserAndMovie(p_user, p_movie);
	}

	public Double getAverageRateByMovie(final Movie p_movie) {
		return getUserMovieRateDAO().getAverageRateByMovie(p_movie);
	}

	public List<UserMovieRate> getUserMovieRateByUser(final User p_user) {
		return getUserMovieRateDAO().getUserMovieRateByUser(p_user);
	}

	/**
	 * ################# USER FOLLOWING #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addUserFollowing(final UserFollowing p_userFollowing) {
		getUserFollowingDAO().addUserFollowing(p_userFollowing);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserFollowing(final UserFollowing p_userFollowing) {
		getUserFollowingDAO().deleteUserFollowing(p_userFollowing);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserFollowing(final UserFollowing p_userFollowing) {
		getUserFollowingDAO().updateUserFollowing(p_userFollowing);
	}

	@Override
	public UserFollowing getUserFriends(final User p_user, final User p_user2) {
		return getUserFollowingDAO().getUserFriends(p_user, p_user2);
	}

	/**
	 * ################# USER FAVORITE MOVIE #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addUserFavoriteMovie(final UserFavoriteMovie p_userFavoriteMovie) {
		getUserFavoriteMovieDAO().addUserFavoriteMovie(p_userFavoriteMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserFavoriteMovie(final UserFavoriteMovie p_userFavoriteMovie) {
		getUserFavoriteMovieDAO().deleteUserFavoriteMovie(p_userFavoriteMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserFavoriteMovie(final UserFavoriteMovie p_userFavoriteMovie) {
		getUserFavoriteMovieDAO().updateUserFavoriteMovie(p_userFavoriteMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public UserFavoriteMovie getMovieInUserFavorites(final User p_user, final Movie p_movie) {
		return getUserFavoriteMovieDAO().getMovieInUserFavorites(p_user, p_movie);
	}

	@Transactional(readOnly = false)
	@Override
	public List<UserFavoriteMovie> getUserFavoriteMovieByUser(final User p_user) {
		return getUserFavoriteMovieDAO().getUserFavoriteMovieByUser(p_user);
	}

	/**
	 * ################# USER MOVIE WISHLIST #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addUserMovieWishlist(final UserMovieWishlist p_userMovieWishlist) {
		getUserMovieWishlistDAO().addUserMovieWishlist(p_userMovieWishlist);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserMovieWishlist(final UserMovieWishlist p_userMovieWishlist) {
		getUserMovieWishlistDAO().deleteUserMovieWishlist(p_userMovieWishlist);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserMovieWishlist(final UserMovieWishlist p_userMovieWishlist) {
		getUserMovieWishlistDAO().updateUserMovieWishlist(p_userMovieWishlist);
	}

	@Transactional(readOnly = false)
	@Override
	public UserMovieWishlist getMovieInUserWishlist(final User p_user, final Movie p_movie) {
		return getUserMovieWishlistDAO().getMovieInUserWishlist(p_user, p_movie);
	}

	@Transactional(readOnly = false)
	@Override
	public List<UserMovieWishlist> getUserMovieWishlistByUser(final User p_user) {
		return getUserMovieWishlistDAO().getUserMovieWishlistByUser(p_user);
	}

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
	 * @return the userMovieWishlistDAO
	 */
	public IUserMovieWishlistDAO getUserMovieWishlistDAO() {
		return userMovieWishlistDAO;
	}

	/**
	 * @param p_userDAO the userDAO to set
	 */
	public void setUserDAO(final IUserDAO p_userDAO) {
		this.userDAO = p_userDAO;
	}

	/**
	 * @param p_userMovieReviewDAO the userMovieReviewDAO to set
	 */
	public void setUserMovieReviewDAO(final IUserMovieReviewDAO p_userMovieReviewDAO) {
		this.userMovieReviewDAO = p_userMovieReviewDAO;
	}

	/**
	 * @param p_userMovieRateDAO the userMovieRateDAO to set
	 */
	public void setUserMovieRateDAO(final IUserMovieRateDAO p_userMovieRateDAO) {
		this.userMovieRateDAO = p_userMovieRateDAO;
	}

	/**
	 * @param p_userFollowingDAO the userFollowingDAO to set
	 */
	public void setUserFollowingDAO(final IUserFollowingDAO p_userFollowingDAO) {
		this.userFollowingDAO = p_userFollowingDAO;
	}

	/**
	 * @param p_userFavoriteMovieDAO the userFavoriteMovieDAO to set
	 */
	public void setUserFavoriteMovieDAO(final IUserFavoriteMovieDAO p_userFavoriteMovieDAO) {
		this.userFavoriteMovieDAO = p_userFavoriteMovieDAO;
	}

	/**
	 * @param p_userMovieWishlistDAO the userMovieWishlistDAO to set
	 */
	public void setUserMovieWishlistDAO(final IUserMovieWishlistDAO p_userMovieWishlistDAO) {
		this.userMovieWishlistDAO = p_userMovieWishlistDAO;
	}

}
