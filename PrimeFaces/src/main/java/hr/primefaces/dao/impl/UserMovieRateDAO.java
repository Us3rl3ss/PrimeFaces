package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserMovieRateDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieRate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserMovieRateDAO implements IUserMovieRateDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_USER_MOVIE_RATE_BY_USER_AND_MOVIE = "from UserMovieRate where user_id = :user_id and movie_id = :movie_id";
	private final String GET_AVERATE_RATE_BY_MOVIE = "select ROUND(avg(rate)) from UserMovieRate where movie_id = :movie_id";
	private final String GET_USER_MOVIE_RATE_BY_USER = "from UserMovieRate usr join usr.movie where user_id = :user_id order by usr.created desc";

	@Override
	public void addUserMovieRate(final UserMovieRate p_userMovieRate) {
		getSessionFactory().getCurrentSession().save(p_userMovieRate);
	}

	@Override
	public void deleteUserMovieRate(final UserMovieRate p_userMovieRate) {
		getSessionFactory().getCurrentSession().delete(p_userMovieRate);
	}

	@Override
	public void updateUserMovieRate(final UserMovieRate p_userMovieRate) {
		getSessionFactory().getCurrentSession().update(p_userMovieRate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMovieRate> getUserMovieRateByUserAndMovie(final User p_user, final Movie p_movie) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_MOVIE_RATE_BY_USER_AND_MOVIE);
		query.setParameter("user_id", p_user.getId());
		query.setParameter("movie_id", p_movie.getId());
		return query.list();
	}

	@Override
	public Double getAverageRateByMovie(final Movie p_movie) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_AVERATE_RATE_BY_MOVIE);
		query.setParameter("movie_id", p_movie.getId());
		return (Double) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMovieRate> getUserMovieRateByUser(final User p_user) {

		final List<Object[]> list = getSessionFactory().getCurrentSession().createQuery(GET_USER_MOVIE_RATE_BY_USER)
				.setParameter("user_id", p_user.getId()).list();

		final List<UserMovieRate> result = new ArrayList<UserMovieRate>();
		for (Object[] arr : list) {

			final UserMovieRate umr = (UserMovieRate) arr[0];
			final Movie m = (Movie) arr[1];

			umr.setMovie(m);

			result.add(umr);
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
