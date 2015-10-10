package hr.primefaces.bean;

import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserFollowing;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserFavoriteMovieService;
import hr.primefaces.service.IUserFollowingService;
import hr.primefaces.service.IUserMovieRateService;
import hr.primefaces.service.IUserMovieReviewService;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "usersMB")
@ViewScoped
public class UsersView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSession}")
	UserSession userSession;

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

	@ManagedProperty(value = "#{UserFollowingService}")
	IUserFollowingService userFollowingService;

	private List<UserMovieRate> userMovieRateList;
	private List<UserMovieReview> userMovieReviewList;
	private List<UserFavoriteMovie> userFavoriteMovieList;

	private UserMovieRate selectedRate;
	private UserMovieReview selectedReview;
	private UserFavoriteMovie selectedFavorite;

	private Integer averageRate = 0;

	private User user;

	private boolean inFollowList = false;

	@PostConstruct
	public void init() {
	}

	public void pretrazi() {

		// System.out.println("PRETRAŽI");
		//
		// Iterator<Projection> iter =
		// dropDownMB.getProjectionList().iterator();
		//
		// this.projectionList = new ArrayList<Projection>();
		//
		// while (iter.hasNext()) {
		//
		// Projection p = iter.next();
		//
		// if (p.getTheater().getId().equals(theater.getId()))
		// this.projectionList.add(p);
		// }

	}

	public List<User> completeUser(String input) {
		List<User> list = (List<User>) userService.getUserByUsername(input);
		return list;
	}

	public void handleSelect() {
		System.out.print("test");
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage("Item Selected", event.getObject()
								.toString()));

		this.userMovieRateList = userMovieRateService
				.getUserMovieRateByUser(this.user);
		this.userMovieReviewList = userMovieReviewService
				.getUserMovieReviewByUser(this.user);
		this.userFavoriteMovieList = userFavoriteMovieService
				.getUserFavoriteMovieByUser(this.user);

		boolean inFollowList = isInFollowList(userSession.getUser(), this.user);

		if (inFollowList) {
			System.out.println("u follow listi");
			this.inFollowList = true;
		} else {
			this.inFollowList = false;
		}
	}

	public boolean isInFollowList(User loggedUser, User currUser) {

		boolean result = false;

		List<User> list = userService.getUserFollow(loggedUser, currUser);

		if (list.size() > 0) {

			result = true;
		}

		return result;
	}

	public void addToFollowList() {

		Integer user_id = userSession.getUser().getId();
		Integer follow_id = this.user.getId();

		UserFollowing uf = new UserFollowing();
		uf.setUser_id(user_id);
		uf.setFollow_id(follow_id);
		uf.setCreated(new Date());
		
		userFollowingService.addUserFollowing(uf);

		System.out.println("ADDED TO FOLLOW LIST");
		isInFollowList();
	}

	public void calculateAverageRate() {

		int averageRate = 0;

		Double avg = userMovieRateService
				.getAverageRateByMovie(this.selectedRate.getMovie());

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public void setUserFavoriteMovieList(
			List<UserFavoriteMovie> userFavoriteMovieList) {
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

	public boolean isInFollowList() {
		return inFollowList;
	}

	public void setInFollowList(boolean inFollowList) {
		this.inFollowList = inFollowList;
	}

	public IUserFollowingService getUserFollowingService() {
		return userFollowingService;
	}

	public void setUserFollowingService(
			IUserFollowingService userFollowingService) {
		this.userFollowingService = userFollowingService;
	}

}