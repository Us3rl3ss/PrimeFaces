package hr.primefaces.service.impl;

import hr.primefaces.dao.IUserMovieReviewDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IUserMovieReviewService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "UserMovieReviewService")
@ApplicationScoped
@Transactional(readOnly = true)
public class UserMovieReviewService implements IUserMovieReviewService,
		Serializable {

	private static final long serialVersionUID = 1L;

	IUserMovieReviewDAO userMovieReviewDAO;
	List<UserMovieReview> userMovieReviewList;

	public UserMovieReviewService() {
	}

	public List<UserMovieReview> getUserMovieReviewList() {
		return userMovieReviewList;
	}

	public void setUserMovieReviewList(List<UserMovieReview> userMovieReviewList) {
		this.userMovieReviewList = userMovieReviewList;
	}

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

	public IUserMovieReviewDAO getUserMovieReviewDAO() {
		return userMovieReviewDAO;
	}

	public void setUserMovieReviewDAO(IUserMovieReviewDAO userMovieReviewDAO) {
		this.userMovieReviewDAO = userMovieReviewDAO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
}
