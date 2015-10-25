package hr.primefaces.bean;

import hr.primefaces.imdb.ImdbJsonModel;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.model.UserMovieWishlist;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserService;
import hr.primefaces.util.Check;
import hr.primefaces.util.ObjectRemapper;

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

	private List<Movie> movieList;
	private Movie movie;
	private UserMovieRate userMovieRate;
	private UserMovieReview userMovieReview;
	private List<UserMovieReview> userMovieReviewList;
	private String movieInfoRenderCss;
	private Integer averageRate;
	private boolean inFavorites;
	private boolean inWishlist;
	private boolean movieInfoFormRender;
	private boolean rateDisabled;
	private boolean reviewDisabled;
	private ImdbJsonModel imdbMovie;

	/**
	 * init
	 */
	@PostConstruct
	public void init() {

		setMovie(new Movie());
		setUserMovieRate(new UserMovieRate());
		setUserMovieReview(new UserMovieReview());
		setMovieInfoRenderCss("display:none;");
		setAverageRate(0);
		setInFavorites(false);
		setMovieInfoFormRender(false);
		setRateDisabled(false);
		setReviewDisabled(false);
		setImdbMovie(new ImdbJsonModel());
	}

	/**
	 * search
	 */
	public void search() {

		if (getUserSession().getUser() != null) {

			if (isRateSaved(getUserSession().getUser(), getMovie())) {

				setRateDisabled(true);
			}
			else {

				setRateDisabled(false);
			}

			if (isReviewSaved(getUserSession().getUser(), getMovie())) {

				setReviewDisabled(true);
			}
			else {

				setReviewDisabled(false);
			}

			if (isInFavorites(getUserSession().getUser(), getMovie())) {

				setInFavorites(true);
			}
			else {

				setInFavorites(false);
			}

			if (isInWishlist(getUserSession().getUser(), getMovie())) {

				setInWishlist(true);
			}
			else {

				setInWishlist(false);
			}
		}

		setAverageRate(calculateAverageRate(getMovie()));
		setUserMovieReviewList(getAllMovieReviews(getMovie()));
	}

	/**
	 * insertMovieFromImdb
	 * @param p_movie
	 * @return
	 */
	public Movie insertMovieFromImdb(final Movie p_movie) {

		Movie result = new Movie();

		if (p_movie != null) {

			if (!Check.isNullOrEmpthy(p_movie.getImdbId())) {

				final Movie tempMovie = getMovieService().getMovieByImdbId(p_movie.getImdbId());

				if (tempMovie != null) { //film već postoji u bazi podataka

					result = tempMovie;
				}
				else { //film ne postoji u bazi podataka

					getMovieService().addMovie(p_movie);
					result = p_movie;
				}
			}
		}

		return result;
	}

	/**
	 * calculateAverageRate
	 * @param p_movie
	 * @return
	 */
	public int calculateAverageRate(final Movie p_movie) {

		int avgRate = 0;

		final Double avg = getUserService().getAverageRateByMovie(p_movie);

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

		return avgRate;
	}

	/**
	 * addReview
	 */
	public void addReview() {

		if (getUserMovieReview().getReview() != null) {

			setMovie(insertMovieFromImdb(getMovie()));

			getUserMovieReview().setUser(getUserSession().getUser());
			getUserMovieReview().setMovie(getMovie());
			getUserMovieReview().setCreated(new Date());

			getUserService().addUserMovieReview(getUserMovieReview());

			search();
		}
	}

	/**
	 * addToFavorites
	 */
	public void addToFavorites() {

		setMovie(insertMovieFromImdb(getMovie()));

		final UserFavoriteMovie ufm = new UserFavoriteMovie();
		ufm.setMovie(getMovie());
		ufm.setUser(getUserSession().getUser());
		ufm.setCreated(new Date());
		getUserService().addUserFavoriteMovie(ufm);

		search();
	}

	/**
	 * addToWishlist
	 */
	public void addToWishlist() {

		setMovie(insertMovieFromImdb(getMovie()));

		final UserMovieWishlist umw = new UserMovieWishlist();
		umw.setMovie(getMovie());
		umw.setUser(getUserSession().getUser());
		umw.setCreated(new Date());
		getUserService().addUserMovieWishlist(umw);

		search();
	}

	/**
	 * removeFromFavorites
	 */
	public void removeFromFavorites() {

		if (getMovie() != null) {

			if (!Check.isNullOrEmpthy(getMovie().getImdbId())) {

				final Movie tempMovie = getMovieService().getMovieByImdbId(getMovie().getImdbId());

				if (tempMovie != null) { //film već postoji u bazi podataka

					final UserFavoriteMovie ufm = getUserService().getMovieInUserFavorites(getUserSession().getUser(), tempMovie);

					if (ufm != null) {

						getUserService().deleteUserFavoriteMovie(ufm);
						search();
					}
				}
			}
		}
	}

	/**
	 * removeFromWishlist
	 */
	public void removeFromWishlist() {

		if (getMovie() != null) {

			if (!Check.isNullOrEmpthy(getMovie().getImdbId())) {

				final Movie tempMovie = getMovieService().getMovieByImdbId(getMovie().getImdbId());

				if (tempMovie != null) { //film već postoji u bazi podataka

					final UserMovieWishlist umw = getUserService().getMovieInUserWishlist(getUserSession().getUser(), tempMovie);

					if (umw != null) {

						getUserService().deleteUserMovieWishlist(umw);
						search();
					}
				}
			}
		}
	}

	/**
	 * isRateSaved
	 * @param p_user
	 * @param p_movie
	 * @return
	 */
	public boolean isRateSaved(final User p_user, final Movie p_movie) {

		boolean result = false;

		if (p_movie != null) {

			if (!Check.isNullOrEmpthy(p_movie.getImdbId())) {

				final Movie tempMovie = getMovieService().getMovieByImdbId(p_movie.getImdbId());

				if (tempMovie != null) { //film već postoji u bazi podataka

					final List<UserMovieRate> list = getUserService().getUserMovieRateByUserAndMovie(p_user, tempMovie);

					if (list.size() > 0) {

						setUserMovieRate(list.get(0));
						result = true;
					}
					else {

						setUserMovieRate(new UserMovieRate());
					}
				}
			}
		}

		return result;
	}

	/**
	 * isReviewSaved
	 * @param p_user
	 * @param p_movie
	 * @return
	 */
	public boolean isReviewSaved(final User p_user, final Movie p_movie) {

		boolean result = false;

		if (p_movie != null) {

			if (!Check.isNullOrEmpthy(p_movie.getImdbId())) {

				final Movie tempMovie = getMovieService().getMovieByImdbId(p_movie.getImdbId());

				if (tempMovie != null) { //film već postoji u bazi podataka

					final List<UserMovieReview> list = getUserService().getUserMovieReviewByUserAndMovie(p_user, tempMovie);

					if (list.size() > 0) {

						setUserMovieReview(list.get(0));
						result = true;
					}
					else {

						setUserMovieReview(new UserMovieReview());
					}
				}
			}
		}

		return result;
	}

	/**
	 * isInFavorites
	 * @param p_user
	 * @param p_movie
	 * @return
	 */
	public boolean isInFavorites(final User p_user, final Movie p_movie) {

		boolean result = false;

		if (p_movie != null) {

			if (!Check.isNullOrEmpthy(p_movie.getImdbId())) {

				final Movie tempMovie = getMovieService().getMovieByImdbId(p_movie.getImdbId());

				if (tempMovie != null) { //film već postoji u bazi podataka

					if (getUserService().getMovieInUserFavorites(p_user, tempMovie) != null) {

						result = true;
					}
				}
			}
		}

		return result;
	}

	/**
	 * isInWishlist
	 * @param p_user
	 * @param p_movie
	 * @return
	 */
	public boolean isInWishlist(final User p_user, final Movie p_movie) {

		boolean result = false;

		if (p_movie != null) {

			if (!Check.isNullOrEmpthy(p_movie.getImdbId())) {

				final Movie tempMovie = getMovieService().getMovieByImdbId(p_movie.getImdbId());

				if (tempMovie != null) { //film već postoji u bazi podataka

					if (getUserService().getMovieInUserWishlist(p_user, tempMovie) != null) {

						result = true;
					}
				}
			}
		}

		return result;
	}

	/**
	 * getAllMovieReviews
	 * @param p_movie
	 * @return
	 */
	private List<UserMovieReview> getAllMovieReviews(final Movie p_movie) {

		List<UserMovieReview> result = getUserService().getAllMovieReviews(p_movie);

		if (result == null) {
			result = new ArrayList<UserMovieReview>();
		}

		return result;
	}

	/**
	 * onItemSelect
	 * @param p_event
	 */
	public void onItemSelect(final AjaxBehaviorEvent p_event) {

		//Remaping imdbJsonModel -> Movie object
		setMovie(ObjectRemapper.imdbToMovieObj(getImdbMovie()));

		search();
		setMovieInfoRenderCss("");
		setMovieInfoFormRender(true);
	}

	/**
	 * onRate
	 */
	public void onRate() {

		if (getUserMovieRate().getRate() > 0) {

			setMovie(insertMovieFromImdb(getMovie()));

			getUserMovieRate().setUser(getUserSession().getUser());
			getUserMovieRate().setMovie(getMovie());
			getUserMovieRate().setCreated(new Date());

			getUserService().addUserMovieRate(getUserMovieRate());

			search();
		}
	}

	/**
	 * onCancel
	 */
	public void onCancel() {
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
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @return the movieList
	 */
	public List<Movie> getMovieList() {
		return movieList;
	}

	/**
	 * @return the movieInfoRenderCss
	 */
	public String getMovieInfoRenderCss() {
		return movieInfoRenderCss;
	}

	/**
	 * @return the userMovieRate
	 */
	public UserMovieRate getUserMovieRate() {
		return userMovieRate;
	}

	/**
	 * @return the rateDisabled
	 */
	public boolean isRateDisabled() {
		return rateDisabled;
	}

	/**
	 * @return the userMovieReview
	 */
	public UserMovieReview getUserMovieReview() {
		return userMovieReview;
	}

	/**
	 * @return the reviewDisabled
	 */
	public boolean isReviewDisabled() {
		return reviewDisabled;
	}

	/**
	 * @return the averageRate
	 */
	public Integer getAverageRate() {
		return averageRate;
	}

	/**
	 * @return the inFavorites
	 */
	public boolean isInFavorites() {
		return inFavorites;
	}

	/**
	 * @return the userMovieReviewList
	 */
	public List<UserMovieReview> getUserMovieReviewList() {
		return userMovieReviewList;
	}

	/**
	 * @return the movieInfoFormRender
	 */
	public boolean isMovieInfoFormRender() {
		return movieInfoFormRender;
	}

	/**
	 * @return the imdbMovie
	 */
	public ImdbJsonModel getImdbMovie() {
		return imdbMovie;
	}

	/**
	 * @return the inWishlist
	 */
	public boolean isInWishlist() {
		return inWishlist;
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
	 * @param p_movie the movie to set
	 */
	public void setMovie(final Movie p_movie) {
		this.movie = p_movie;
	}

	/**
	 * @param p_movieList the movieList to set
	 */
	public void setMovieList(final List<Movie> p_movieList) {
		this.movieList = p_movieList;
	}

	/**
	 * @param p_movieInfoRenderCss the movieInfoRenderCss to set
	 */
	public void setMovieInfoRenderCss(final String p_movieInfoRenderCss) {
		this.movieInfoRenderCss = p_movieInfoRenderCss;
	}

	/**
	 * @param p_userMovieRate the userMovieRate to set
	 */
	public void setUserMovieRate(final UserMovieRate p_userMovieRate) {
		this.userMovieRate = p_userMovieRate;
	}

	/**
	 * @param p_rateDisabled the rateDisabled to set
	 */
	public void setRateDisabled(final boolean p_rateDisabled) {
		this.rateDisabled = p_rateDisabled;
	}

	/**
	 * @param p_userMovieReview the userMovieReview to set
	 */
	public void setUserMovieReview(final UserMovieReview p_userMovieReview) {
		this.userMovieReview = p_userMovieReview;
	}

	/**
	 * @param p_reviewDisabled the reviewDisabled to set
	 */
	public void setReviewDisabled(final boolean p_reviewDisabled) {
		this.reviewDisabled = p_reviewDisabled;
	}

	/**
	 * @param p_averageRate the averageRate to set
	 */
	public void setAverageRate(final Integer p_averageRate) {
		this.averageRate = p_averageRate;
	}

	/**
	 * @param p_inFavorites the inFavorites to set
	 */
	public void setInFavorites(final boolean p_inFavorites) {
		this.inFavorites = p_inFavorites;
	}

	/**
	 * @param p_userMovieReviewList the userMovieReviewList to set
	 */
	public void setUserMovieReviewList(final List<UserMovieReview> p_userMovieReviewList) {
		this.userMovieReviewList = p_userMovieReviewList;
	}

	/**
	 * @param p_movieInfoFormRender the movieInfoFormRender to set
	 */
	public void setMovieInfoFormRender(final boolean p_movieInfoFormRender) {
		this.movieInfoFormRender = p_movieInfoFormRender;
	}

	/**
	 * @param p_imdbMovie the imdbMovie to set
	 */
	public void setImdbMovie(final ImdbJsonModel p_imdbMovie) {
		this.imdbMovie = p_imdbMovie;
	}

	/**
	 * @param p_inWishlist the inWishlist to set
	 */
	public void setInWishlist(final boolean p_inWishlist) {
		this.inWishlist = p_inWishlist;
	}

}