package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserFavoriteMovieDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserFavoriteMovieDAO implements IUserFavoriteMovieDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getSessionFactory().getCurrentSession().save(userFavoriteMovie);
	}

	@Override
	public void deleteUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getSessionFactory().getCurrentSession().delete(userFavoriteMovie);
	}

	@Override
	public void updateUserFavoriteMovie(UserFavoriteMovie userFavoriteMovie) {
		getSessionFactory().getCurrentSession().update(userFavoriteMovie);
	}

	@Override
	public UserFavoriteMovie getMovieInUserFavorites(User user, Movie movie) {

		String query = "from UserFavoriteMovie " + "where user_id = :userId " + "and movie_id = :movieId";

		return (UserFavoriteMovie) getSessionFactory().getCurrentSession().createQuery(query).setParameter("userId", user.getId()).setParameter("movieId", movie.getId())
				.uniqueResult();
	}

	@Override
	public List<UserFavoriteMovie> getUserFavoriteMovieByUser(User user) {

		String query = "from UserFavoriteMovie ufm " + "join ufm.movie " + "where user_id = :userId " + "order by ufm.created desc";

		List<Object[]> list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("userId", user.getId()).list();

		List<UserFavoriteMovie> result = new ArrayList<UserFavoriteMovie>();
		for (Object[] arr : list) {

			UserFavoriteMovie ufm = (UserFavoriteMovie) arr[0];
			Movie m = (Movie) arr[1];

			ufm.setMovie(m);

			result.add(ufm);
		}

		return result;
	}

}
