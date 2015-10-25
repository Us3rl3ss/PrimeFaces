package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserMovieWishlistDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieWishlist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserMovieWishlistDAO implements IUserMovieWishlistDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_MOVIE_IN_USER_WISHLIST = "from UserMovieWishlist where user_id = :user_id and movie_id = :movie_id";
	private final String GET_USER_MOVIE_WISHLIST_BY_USER = "from UserMovieWishlist umw join umw.movie where user_id = :user_id order by umw.created desc";

	@Override
	public void addUserMovieWishlist(final UserMovieWishlist p_userMovieWishlist) {
		getSessionFactory().getCurrentSession().save(p_userMovieWishlist);
	}

	@Override
	public void deleteUserMovieWishlist(final UserMovieWishlist p_userMovieWishlist) {
		getSessionFactory().getCurrentSession().delete(p_userMovieWishlist);
	}

	@Override
	public void updateUserMovieWishlist(final UserMovieWishlist p_userMovieWishlist) {
		getSessionFactory().getCurrentSession().update(p_userMovieWishlist);
	}

	@Override
	public UserMovieWishlist getMovieInUserWishlist(final User p_user, final Movie p_movie) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_MOVIE_IN_USER_WISHLIST);
		query.setParameter("user_id", p_user.getId());
		query.setParameter("movie_id", p_movie.getId());
		return (UserMovieWishlist) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMovieWishlist> getUserMovieWishlistByUser(final User p_user) {

		final List<Object[]> list = getSessionFactory().getCurrentSession().createQuery(GET_USER_MOVIE_WISHLIST_BY_USER)
				.setParameter("user_id", p_user.getId()).list();

		final List<UserMovieWishlist> result = new ArrayList<UserMovieWishlist>();
		for (Object[] arr : list) {

			final UserMovieWishlist ufm = (UserMovieWishlist) arr[0];
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
