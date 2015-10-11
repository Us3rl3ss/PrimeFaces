package hr.primefaces.service.impl;

import hr.primefaces.dao.IUserFavoriteMovieDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.service.IUserFavoriteMovieService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "UserFavoriteMovieService")
@ApplicationScoped
@Transactional(readOnly = true)
public class UserFavoriteMovieService implements IUserFavoriteMovieService,
		Serializable {

	private static final long serialVersionUID = 1L;

	IUserFavoriteMovieDAO userFavoriteMovieDAO;
	List<UserFavoriteMovie> userFavoriteMovieList;

	public UserFavoriteMovieService() {
	}

	public List<UserFavoriteMovie> getUserFavoriteMovieList() {
		return userFavoriteMovieList;
	}

	public void setUserFavoriteMovieList(
			List<UserFavoriteMovie> userFavoriteMovieList) {
		this.userFavoriteMovieList = userFavoriteMovieList;
	}

	@Transactional(readOnly = false)
	@Override
	public void addUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getUserFavoriteMovieDAO().addUserFavoriteMovie(userFavoriteMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getUserFavoriteMovieDAO().deleteUserFavoriteMovie(userFavoriteMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getUserFavoriteMovieDAO().updateUserFavoriteMovie(userFavoriteMovie);
	}

	public IUserFavoriteMovieDAO getUserFavoriteMovieDAO() {
		return userFavoriteMovieDAO;
	}

	public void setUserFavoriteMovieDAO(
			IUserFavoriteMovieDAO userFavoriteMovieDAO) {
		this.userFavoriteMovieDAO = userFavoriteMovieDAO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Transactional(readOnly = false)
	@Override
	public UserFavoriteMovie getMovieInUserFavorites(User user, Movie movie) {
		return getUserFavoriteMovieDAO().getMovieInUserFavorites(user, movie);
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<UserFavoriteMovie> getUserFavoriteMovieByUser(User user) {
		return getUserFavoriteMovieDAO().getUserFavoriteMovieByUser(user);
	}
}
