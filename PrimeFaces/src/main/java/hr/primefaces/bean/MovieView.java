package hr.primefaces.bean;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "movieMB")
@ViewScoped
public class MovieView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	private Movie movie = new Movie();

	private List<Movie> movieList;

	private String movieInfoRenderCss = "display:none;";

	private UserMovieRate userMovieRate = new UserMovieRate();
	private boolean rateDisabled = false;
	private UserMovieReview userMovieReview = new UserMovieReview();
	private boolean reviewDisabled = false;

	private Integer averageRate = 0;

	private boolean inFavorites = false;

	private List<UserMovieReview> userMovieReviewList;

	private boolean movieInfoFormRender = false;

	/**
	 * init
	 */
	@PostConstruct
	public void init() {
	}

	/**
	 * test
	 * 
	 * @param p_input
	 *            test
	 * @return test
	 */
	public List<Movie> completeMovie(final String p_input) {
		return movieService.getMovieByName(p_input);
	}

	public void onItemSelect(AjaxBehaviorEvent event) {

		pretrazi();
		setMovieInfoRenderCss("");

		movieInfoFormRender = true;
	}

	public void pretrazi() {

		if (userSession.getUser() != null) {

			boolean rateSaved = isRateSaved(userSession.getUser(), this.movie);
			boolean reviewSaved = isReviewSaved(userSession.getUser(), this.movie);
			boolean inFavorites = isInFavorites(userSession.getUser(), this.movie);

			if (rateSaved) {
				System.out.println("rate postoji");
				this.rateDisabled = true;
			} else {
				this.rateDisabled = false;
			}

			if (reviewSaved) {
				System.out.println("review postoji");
				this.reviewDisabled = true;
			} else {
				this.reviewDisabled = false;
			}

			if (inFavorites) {
				System.out.println("u favoritima");
				this.inFavorites = true;
			} else {
				this.inFavorites = false;
			}
		}

		this.averageRate = calculateAverageRate(this.movie);
		this.userMovieReviewList = getAllMovieReviews(this.movie);
	}

	public int calculateAverageRate(Movie movie) {

		int averageRate = 0;

		Double avg = userService.getAverageRateByMovie(movie);

		try {

			if (avg != null) {
				averageRate = avg.intValue();
			}

		} catch (NumberFormatException nex) {
			nex.printStackTrace();
			System.out.println("NEX");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("EX");
		}

		return averageRate;
	}

	public boolean isRateSaved(User user, Movie movie) {

		boolean result = false;

		final List<UserMovieRate> list = userService.getUserMovieRateByUserAndMovie(user, movie);

		if (list.size() > 0) {

			this.userMovieRate = list.get(0);
			result = true;
		} else {

			this.userMovieRate = new UserMovieRate();
		}

		return result;
	}

	public boolean isReviewSaved(User user, Movie movie) {

		boolean result = false;

		List<UserMovieReview> list = userService.getUserMovieReviewByUserAndMovie(user, movie);

		if (list.size() > 0) {

			this.userMovieReview = list.get(0);
			result = true;
		} else {

			this.userMovieReview = new UserMovieReview();
		}

		return result;
	}

	public void addReview() {

		if (this.userMovieReview.getReview() != null) {

			this.userMovieReview.setUser(userSession.getUser());
			this.userMovieReview.setMovie(this.movie);
			this.userMovieReview.setCreated(new Date());

			userService.addUserMovieReview(userMovieReview);

			pretrazi();
		}
	}

	public void onRate() {

		if (this.userMovieRate.getRate() > 0) {

			this.userMovieRate.setUser(userSession.getUser());
			this.userMovieRate.setMovie(this.movie);
			this.userMovieRate.setCreated(new Date());

			userService.addUserMovieRate(userMovieRate);

			pretrazi();
		}
	}

	public void onCancel() {
	}

	public void addToFavorites() {

		UserFavoriteMovie ufm = new UserFavoriteMovie();
		ufm.setMovie(this.movie);
		ufm.setUser(userSession.getUser());
		ufm.setCreated(new Date());
		userService.addUserFavoriteMovie(ufm);

		pretrazi();
	}

	public void removeFromFavorites() {

		UserFavoriteMovie ufm = userService.getMovieInUserFavorites(userSession.getUser(), movie);
		userService.deleteUserFavoriteMovie(ufm);

		pretrazi();
	}

	public boolean isInFavorites(User user, Movie movie) {

		boolean result = false;

		UserFavoriteMovie ufm = userService.getMovieInUserFavorites(user, movie);

		if (ufm != null) {

			result = true;
		}

		return result;
	}

	private List<UserMovieReview> getAllMovieReviews(Movie movie) {

		List<UserMovieReview> result = userService.getAllMovieReviews(movie);

		if (result == null) {
			result = new ArrayList<UserMovieReview>();
		}

		return result;
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public UserMovieRate getUserMovieRate() {
		return userMovieRate;
	}

	public void setUserMovieRate(UserMovieRate userMovieRate) {
		this.userMovieRate = userMovieRate;
	}

	public UserMovieReview getUserMovieReview() {
		return userMovieReview;
	}

	public void setUserMovieReview(UserMovieReview userMovieReview) {
		this.userMovieReview = userMovieReview;
	}

	public boolean isRateDisabled() {
		return rateDisabled;
	}

	public void setRateDisabled(boolean rateDisabled) {
		this.rateDisabled = rateDisabled;
	}

	public boolean isReviewDisabled() {
		return reviewDisabled;
	}

	public void setReviewDisabled(boolean reviewDisabled) {
		this.reviewDisabled = reviewDisabled;
	}

	public Integer getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(Integer averageRate) {
		this.averageRate = averageRate;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public boolean isInFavorites() {
		return inFavorites;
	}

	public void setInFavorites(boolean inFavorites) {
		this.inFavorites = inFavorites;
	}

	public List<UserMovieReview> getUserMovieReviewList() {
		return userMovieReviewList;
	}

	public void setUserMovieReviewList(List<UserMovieReview> userMovieReviewList) {
		this.userMovieReviewList = userMovieReviewList;
	}

	public String getMovieInfoRenderCss() {
		return movieInfoRenderCss;
	}

	public void setMovieInfoRenderCss(String movieInfoRenderCss) {
		this.movieInfoRenderCss = movieInfoRenderCss;
	}

	public boolean isMovieInfoFormRender() {
		return movieInfoFormRender;
	}

	public void setMovieInfoFormRender(boolean movieInfoFormRender) {
		this.movieInfoFormRender = movieInfoFormRender;
	}

}