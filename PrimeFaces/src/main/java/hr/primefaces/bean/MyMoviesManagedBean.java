package hr.primefaces.bean;

import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserFavoriteMovieService;
import hr.primefaces.service.IUserMovieRateService;
import hr.primefaces.service.IUserMovieReviewService;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "myMoviesMB")
@ViewScoped
public class MyMoviesManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginMB}")
	LoginManagedBean loginMB;

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	@ManagedProperty(value = "#{UserMovieRateService}")
	IUserMovieRateService userMovieRateService;

	@ManagedProperty(value = "#{UserMovieReviewService}")
	IUserMovieReviewService userMovieReviewService;

	@ManagedProperty(value = "#{UserFavoriteMovieService}")
	IUserFavoriteMovieService userFavoriteMovieService;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	private List<UserMovieRate> userMovieRateList;
	private List<UserMovieReview> userMovieReviewList;
	private List<UserFavoriteMovie> userFavoriteMovieList;

	private UserMovieRate selectedRate;
	private UserMovieReview selectedReview;
	private UserFavoriteMovie selectedFavorite;
	
	private Integer averageRate = 0;

	private User user;

	@PostConstruct
	public void init() {
		this.userMovieRateList = userMovieRateService.getUserMovieRateByUser(loginMB.getUser());
		this.userMovieReviewList = userMovieReviewService.getUserMovieReviewByUser(loginMB.getUser());
		this.userFavoriteMovieList = userFavoriteMovieService.getUserFavoriteMovieByUser(loginMB.getUser());
	}
	
	public void calculateAverageRate() {

		int averageRate = 0;

		Double avg = userMovieRateService.getAverageRateByMovie(this.selectedRate.getMovie());

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

		this.averageRate = averageRate;
	}

	public LoginManagedBean getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginManagedBean loginMB) {
		this.loginMB = loginMB;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<UserMovieRate> getUserMovieRateList() {
		return userMovieRateList;
	}

	public void setUserMovieRateList(List<UserMovieRate> userMovieRateList) {
		this.userMovieRateList = userMovieRateList;
	}

	public List<UserFavoriteMovie> getUserFavoriteMovieList() {
		return userFavoriteMovieList;
	}

	public void setUserFavoriteMovieList(
			List<UserFavoriteMovie> userFavoriteMovieList) {
		this.userFavoriteMovieList = userFavoriteMovieList;
	}

	public List<UserMovieReview> getUserMovieReviewList() {
		return userMovieReviewList;
	}

	public void setUserMovieReviewList(List<UserMovieReview> userMovieReviewList) {
		this.userMovieReviewList = userMovieReviewList;
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

	public IUserFavoriteMovieService getUserFavoriteMovieService() {
		return userFavoriteMovieService;
	}

	public void setUserFavoriteMovieService(
			IUserFavoriteMovieService userFavoriteMovieService) {
		this.userFavoriteMovieService = userFavoriteMovieService;
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public UserMovieRate getSelectedRate() {
		return selectedRate;
	}

	public void setSelectedRate(UserMovieRate selectedRate) {
		this.selectedRate = selectedRate;
	}

	public UserFavoriteMovie getSelectedFavorite() {
		return selectedFavorite;
	}

	public void setSelectedFavorite(UserFavoriteMovie selectedFavorite) {
		this.selectedFavorite = selectedFavorite;
	}

	public UserMovieReview getSelectedReview() {
		return selectedReview;
	}

	public void setSelectedReview(UserMovieReview selectedReview) {
		this.selectedReview = selectedReview;
	}

	public Integer getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(Integer averageRate) {
		this.averageRate = averageRate;
	}

}