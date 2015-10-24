package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserFavoriteMovieDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserFavoriteMovieDAO implements IUserFavoriteMovieDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_MOVIE_IN_USER_FAVORITES = "from UserFavoriteMovie where user_id = :user_id and movie_id = :movie_id";
	private final String GET_USER_FAVORITE_MOVIE_BY_USER = "from UserFavoriteMovie ufm join ufm.movie where user_id = :user_id order by ufm.created desc";

	@Override
	public void addUserFavoriteMovie(final UserFavoriteMovie p_userFavoriteMovie) {
		getSessionFactory().getCurrentSession().save(p_userFavoriteMovie);
	}

	@Override
	public void deleteUserFavoriteMovie(final UserFavoriteMovie p_userFavoriteMovie) {
		getSessionFactory().getCurrentSession().delete(p_userFavoriteMovie);
	}

	@Override
	public void updateUserFavoriteMovie(final UserFavoriteMovie p_userFavoriteMovie) {
		getSessionFactory().getCurrentSession().update(p_userFavoriteMovie);
	}

	@Override
	public UserFavoriteMovie getMovieInUserFavorites(final User p_user, final Movie p_movie) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_MOVIE_IN_USER_FAVORITES);
		query.setParameter("user_id", p_user.getId());
		query.setParameter("movie_id", p_movie.getId());
		return (UserFavoriteMovie) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserFavoriteMovie> getUserFavoriteMovieByUser(final User p_user) {

		final List<Object[]> list = getSessionFactory().getCurrentSession().createQuery(GET_USER_FAVORITE_MOVIE_BY_USER)
				.setParameter("user_id", p_user.getId()).list();

		final List<UserFavoriteMovie> result = new ArrayList<UserFavoriteMovie>();
		for (Object[] arr : list) {

			final UserFavoriteMovie ufm = (UserFavoriteMovie) arr[0];
			final Movie m = (Movie) arr[1];

			ufm.setMovie(m);

			result.add(ufm);
		}

		return result;
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param p_sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(final SessionFactory p_sessionFactory) {
		this.sessionFactory = p_sessionFactory;
	}

}
