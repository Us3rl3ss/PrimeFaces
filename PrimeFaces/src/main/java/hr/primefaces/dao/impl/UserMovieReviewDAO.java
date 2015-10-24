package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserMovieReviewDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieReview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserMovieReviewDAO implements IUserMovieReviewDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_USER_MOVIE_REVIEW_BY_USER_AND_MOVIE = "from UserMovieReview where user_id = :user_id and movie_id = :movie_id";
	private final String GET_ALL_MOVIE_REVIEWS = "from UserMovieReview where movie_id = :movie_id";
	private final String GET_USER_MOVIE_REVIEW_BY_USER = "from UserMovieReview umr join umr.movie where user_id = :user_id order by umr.created desc";

	@Override
	public void addUserMovieReview(final UserMovieReview p_userMovieReview) {
		getSessionFactory().getCurrentSession().save(p_userMovieReview);
	}

	@Override
	public void deleteUserMovieReview(final UserMovieReview p_userMovieReview) {
		getSessionFactory().getCurrentSession().delete(p_userMovieReview);
	}

	@Override
	public void updateUserMovieReview(final UserMovieReview p_userMovieReview) {
		getSessionFactory().getCurrentSession().update(p_userMovieReview);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMovieReview> getUserMovieReviewByUserAndMovie(final User p_user, final Movie p_movie) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_MOVIE_REVIEW_BY_USER_AND_MOVIE);
		query.setParameter("user_id", p_user.getId());
		query.setParameter("movie_id", p_movie.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMovieReview> getAllMovieReviews(final Movie p_movie) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_ALL_MOVIE_REVIEWS);
		query.setParameter("movie_id", p_movie.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMovieReview> getUserMovieReviewByUser(final User p_user) {

		final List<Object[]> list = getSessionFactory().getCurrentSession().createQuery(GET_USER_MOVIE_REVIEW_BY_USER)
				.setParameter("user_id", p_user.getId()).list();

		final List<UserMovieReview> result = new ArrayList<UserMovieReview>();
		for (Object[] arr : list) {

			final UserMovieReview umr = (UserMovieReview) arr[0];
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
