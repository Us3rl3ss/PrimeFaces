package hr.primefaces.service.impl;

import hr.primefaces.dao.IUserMovieRateDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.service.IUserMovieRateService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "UserMovieRateService")
@ApplicationScoped
@Transactional(readOnly = true)
public class UserMovieRateService implements IUserMovieRateService,
		Serializable {

	private static final long serialVersionUID = 1L;

	IUserMovieRateDAO userMovieRateDAO;
	List<UserMovieRate> userMovieRateList;

	public UserMovieRateService() {
	}

	public List<UserMovieRate> getUserMovieRateList() {
		return userMovieRateList;
	}

	public void setUserMovieRateList(List<UserMovieRate> userMovieRateList) {
		this.userMovieRateList = userMovieRateList;
	}

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

	public IUserMovieRateDAO getUserMovieRateDAO() {
		return userMovieRateDAO;
	}

	public void setUserMovieRateDAO(IUserMovieRateDAO userMovieRateDAO) {
		this.userMovieRateDAO = userMovieRateDAO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
}
