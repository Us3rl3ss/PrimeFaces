package hr.primefaces.bean;

import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "myProfileMB")
@ViewScoped
public class MyProfileView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSession}")
	UserSession userSession;

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

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

	private String uploadedFileNames = "";

	@PostConstruct
	public void init() {

		this.user = userSession.getUser();
		postavi();
	}

	public void postavi() {

		this.userMovieRateList = userService.getUserMovieRateByUser(this.user);
		this.userMovieReviewList = userService.getUserMovieReviewByUser(this.user);
		this.userFavoriteMovieList = userService.getUserFavoriteMovieByUser(this.user);
	}

	public void spremi() {

		try {
			user.setUpdated(new Date());
			userService.updateUser(user);

			refreshUserData();
		} catch (Exception ex) { // TODO ERROR HANDLING
			ex.printStackTrace();
		}
	}

	private void refreshUserData() {

		user = userService.getUserById(user.getId());
		RequestContext.getCurrentInstance().execute("$(window).off('beforeunload'); location.reload();");
	}

	public List<User> completeUser(String input) {
		List<User> list = (List<User>) userService.getUserByUsername(input);
		return list;
	}

	public boolean isInFollowList(User loggedUser, User currUser) {

		boolean result = false;

		List<User> list = userService.getUserFollow(loggedUser, currUser);

		if (list.size() > 0) {

			result = true;
		}

		return result;
	}

	public void calculateAverageRate() {

		int averageRate = 0;

		Double avg = userService.getAverageRateByMovie(this.selectedRate.getMovie());

		try {

			if (avg != null)
				averageRate = avg.intValue();

		} catch (NumberFormatException nex) {
			nex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		this.averageRate = averageRate;
	}

	/**
	 * handleFileUpload
	 */
	public void handleFileUpload(FileUploadEvent event) {

		UploadedFile file;
		byte[] byteData = null;

		file = event.getFile();
		try {
			byteData = IOUtils.toByteArray(file.getInputstream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (byteData != null) {

			user.setImage(byteData);
			setUploadedFileNames(getUploadedFileNames() + file.getFileName());
		}
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public List<UserMovieRate> getUserMovieRateList() {
		return userMovieRateList;
	}

	public void setUserMovieRateList(List<UserMovieRate> userMovieRateList) {
		this.userMovieRateList = userMovieRateList;
	}

	public List<UserMovieReview> getUserMovieReviewList() {
		return userMovieReviewList;
	}

	public void setUserMovieReviewList(List<UserMovieReview> userMovieReviewList) {
		this.userMovieReviewList = userMovieReviewList;
	}

	public List<UserFavoriteMovie> getUserFavoriteMovieList() {
		return userFavoriteMovieList;
	}

	public void setUserFavoriteMovieList(List<UserFavoriteMovie> userFavoriteMovieList) {
		this.userFavoriteMovieList = userFavoriteMovieList;
	}

	public UserMovieRate getSelectedRate() {
		return selectedRate;
	}

	public void setSelectedRate(UserMovieRate selectedRate) {
		this.selectedRate = selectedRate;
	}

	public UserMovieReview getSelectedReview() {
		return selectedReview;
	}

	public void setSelectedReview(UserMovieReview selectedReview) {
		this.selectedReview = selectedReview;
	}

	public UserFavoriteMovie getSelectedFavorite() {
		return selectedFavorite;
	}

	public void setSelectedFavorite(UserFavoriteMovie selectedFavorite) {
		this.selectedFavorite = selectedFavorite;
	}

	public Integer getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(Integer averageRate) {
		this.averageRate = averageRate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUploadedFileNames() {
		return uploadedFileNames;
	}

	public void setUploadedFileNames(String uploadedFileNames) {
		this.uploadedFileNames = uploadedFileNames;
	}

}