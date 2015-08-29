package hr.primefaces.bean;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserFavoriteMovieService;
import hr.primefaces.service.IUserMovieRateService;
import hr.primefaces.service.IUserMovieReviewService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "movieMB")
@ViewScoped
public class MovieView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginMB}")
	LoginView loginMB;

	@ManagedProperty(value = "#{UserMovieRateService}")
	IUserMovieRateService userMovieRateService;

	@ManagedProperty(value = "#{UserMovieReviewService}")
	IUserMovieReviewService userMovieReviewService;

	@ManagedProperty(value = "#{UserFavoriteMovieService}")
	IUserFavoriteMovieService userFavoriteMovieService;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	@ManagedProperty("#{dropDownMB}")
	private DropdownMenuManagedBean dropDownMB;

	private Movie movie = new Movie();

	private List<Movie> movieList;

	private UserMovieRate userMovieRate = new UserMovieRate();
	private boolean rateDisabled = false;
	private UserMovieReview userMovieReview = new UserMovieReview();
	private boolean reviewDisabled = false;

	private Integer averageRate = 0;

	private boolean inFavorites = false;
	
	private List<UserMovieReview> userMovieReviewList;

	public void test(){
		System.out.println("test");
	}
	
	@PostConstruct
	public void init() {
		movieList = dropDownMB.getMovieList();
	}

	public void pretrazi() {

		System.out.println("pretraži");

		boolean rateSaved = isRateSaved(loginMB.getUser(), this.movie);
		boolean reviewSaved = isReviewSaved(loginMB.getUser(), this.movie);
		boolean inFavorites = isInFavorites(loginMB.getUser(), this.movie);

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

		this.averageRate = calculateAverageRate(this.movie);
		
		this.userMovieReviewList = getAllMovieReviews(this.movie);
	}

	public int calculateAverageRate(Movie movie) {

		int averageRate = 0;

		Double avg = userMovieRateService.getAverageRateByMovie(movie);

		try {

			if (avg != null)
				averageRate = avg.intValue();

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

		List<UserMovieRate> list = userMovieRateService
				.getUserMovieRateByUserAndMovie(user, movie);

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

		List<UserMovieReview> list = userMovieReviewService
				.getUserMovieReviewByUserAndMovie(user, movie);

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

			this.userMovieReview.setUser(loginMB.getUser());
			this.userMovieReview.setMovie(this.movie);
			this.userMovieReview.setCreated(new Date());

			userMovieReviewService.addUserMovieReview(userMovieReview);
			
			pretrazi();
		}
	}

	public void onRate() {

		if (this.userMovieRate.getRate() > 0) {

			this.userMovieRate.setUser(loginMB.getUser());
			this.userMovieRate.setMovie(this.movie);
			this.userMovieRate.setCreated(new Date());

			userMovieRateService.addUserMovieRate(userMovieRate);
			
			pretrazi();
		}
	}

	public void onCancel() {
	}

	public void addToFavorites() {

		UserFavoriteMovie ufm = new UserFavoriteMovie();

		ufm.setMovie(this.movie);
		ufm.setUser(loginMB.getUser());
		ufm.setCreated(new Date());
		
		userFavoriteMovieService.addUserFavoriteMovie(ufm);

		System.out.println("FAVORITES");
		pretrazi();
	}

	public boolean isInFavorites(User user, Movie movie) {

		boolean result = false;

		List<UserFavoriteMovie> list = userFavoriteMovieService
				.getMovieInUserFavorites(user, movie);

		if (list.size() > 0) {

			result = true;
		}

		return result;
	}
	
	
	private List<UserMovieReview> getAllMovieReviews(Movie movie) {

		List<UserMovieReview> result = userMovieReviewService
				.getAllMovieReviews(movie);

		return result;
	}
	
	
	

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public DropdownMenuManagedBean getDropDownMB() {
		return dropDownMB;
	}

	public void setDropDownMB(DropdownMenuManagedBean dropDownMB) {
		this.dropDownMB = dropDownMB;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LoginView getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginView loginMB) {
		this.loginMB = loginMB;
	}

	public UserMovieRate getUserMovieRate() {
		return userMovieRate;
	}

	public void setUserMovieRate(UserMovieRate userMovieRate) {
		this.userMovieRate = userMovieRate;
	}

	public IUserMovieRateService getUserMovieRateService() {
		return userMovieRateService;
	}

	public void setUserMovieRateService(
			IUserMovieRateService userMovieRateService) {
		this.userMovieRateService = userMovieRateService;
	}

	public IUserMovieReviewService getUserMovieReviewService() {
		return userMovieReviewService;
	}

	public void setUserMovieReviewService(
			IUserMovieReviewService userMovieReviewService) {
		this.userMovieReviewService = userMovieReviewService;
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

	public IUserFavoriteMovieService getUserFavoriteMovieService() {
		return userFavoriteMovieService;
	}

	public void setUserFavoriteMovieService(
			IUserFavoriteMovieService userFavoriteMovieService) {
		this.userFavoriteMovieService = userFavoriteMovieService;
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

}