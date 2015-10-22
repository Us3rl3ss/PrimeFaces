package hr.primefaces.bean;

import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "myMoviesMB")
@ViewScoped
public class MyMoviesView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	private User user;
	private List<UserMovieRate> userMovieRateList;
	private List<UserMovieReview> userMovieReviewList;
	private List<UserFavoriteMovie> userFavoriteMovieList;
	private UserMovieRate selectedRate;
	private UserMovieReview selectedReview;
	private UserFavoriteMovie selectedFavorite;
	private Integer averageRate;

	@PostConstruct
	public void init() {

		setAverageRate(0);
		setUserMovieRateList(getUserService().getUserMovieRateByUser(getUserSession().getUser()));
		setUserMovieReviewList(getUserService().getUserMovieReviewByUser(getUserSession().getUser()));
		setUserFavoriteMovieList(getUserService().getUserFavoriteMovieByUser(getUserSession().getUser()));
	}

	/**
	 * calculateAverageRate
	 */
	public void calculateAverageRate() {

		int avgRate = 0;

		final Double avg = getUserService().getAverageRateByMovie(getSelectedRate().getMovie());

		try {

			if (avg != null) {
				avgRate = avg.intValue();
			}
		}
		catch (NumberFormatException nex) {
			nex.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		setAverageRate(avgRate);
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the userSession
	 */
	public UserSession getUserSession() {
		return userSession;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the userMovieRateList
	 */
	public List<UserMovieRate> getUserMovieRateList() {
		return userMovieRateList;
	}

	/**
	 * @return the userMovieReviewList
	 */
	public List<UserMovieReview> getUserMovieReviewList() {
		return userMovieReviewList;
	}

	/**
	 * @return the userFavoriteMovieList
	 */
	public List<UserFavoriteMovie> getUserFavoriteMovieList() {
		return userFavoriteMovieList;
	}

	/**
	 * @return the selectedRate
	 */
	public UserMovieRate getSelectedRate() {
		return selectedRate;
	}

	/**
	 * @return the selectedReview
	 */
	public UserMovieReview getSelectedReview() {
		return selectedReview;
	}

	/**
	 * @return the selectedFavorite
	 */
	public UserFavoriteMovie getSelectedFavorite() {
		return selectedFavorite;
	}

	/**
	 * @return the averageRate
	 */
	public Integer getAverageRate() {
		return averageRate;
	}

	/**
	 * @param p_userSession the userSession to set
	 */
	public void setUserSession(final UserSession p_userSession) {
		this.userSession = p_userSession;
	}

	/**
	 * @param p_userService the userService to set
	 */
	public void setUserService(final IUserService p_userService) {
		this.userService = p_userService;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_user the user to set
	 */
	public void setUser(final User p_user) {
		this.user = p_user;
	}

	/**
	 * @param p_userMovieRateList the userMovieRateList to set
	 */
	public void setUserMovieRateList(final List<UserMovieRate> p_userMovieRateList) {
		this.userMovieRateList = p_userMovieRateList;
	}

	/**
	 * @param p_userMovieReviewList the userMovieReviewList to set
	 */
	public void setUserMovieReviewList(final List<UserMovieReview> p_userMovieReviewList) {
		this.userMovieReviewList = p_userMovieReviewList;
	}

	/**
	 * @param p_userFavoriteMovieList the userFavoriteMovieList to set
	 */
	public void setUserFavoriteMovieList(final List<UserFavoriteMovie> p_userFavoriteMovieList) {
		this.userFavoriteMovieList = p_userFavoriteMovieList;
	}

	/**
	 * @param p_selectedRate the selectedRate to set
	 */
	public void setSelectedRate(final UserMovieRate p_selectedRate) {
		this.selectedRate = p_selectedRate;
	}

	/**
	 * @param p_selectedReview the selectedReview to set
	 */
	public void setSelectedReview(final UserMovieReview p_selectedReview) {
		this.selectedReview = p_selectedReview;
	}

	/**
	 * @param p_selectedFavorite the selectedFavorite to set
	 */
	public void setSelectedFavorite(final UserFavoriteMovie p_selectedFavorite) {
		this.selectedFavorite = p_selectedFavorite;
	}

	/**
	 * @param p_averageRate the averageRate to set
	 */
	public void setAverageRate(final Integer p_averageRate) {
		this.averageRate = p_averageRate;
	}

}