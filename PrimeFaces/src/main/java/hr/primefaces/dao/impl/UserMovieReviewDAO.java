package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserMovieReviewDAO;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieReview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserMovieReviewDAO implements IUserMovieReviewDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUserMovieReview(UserMovieReview userMovieReview) {
		getSessionFactory().getCurrentSession().save(userMovieReview);
	}

	@Override
	public void deleteUserMovieReview(UserMovieReview userMovieReview) {
		getSessionFactory().getCurrentSession().delete(userMovieReview);
	}

	@Override
	public void updateUserMovieReview(UserMovieReview userMovieReview) {
		getSessionFactory().getCurrentSession().update(userMovieReview);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public List<UserMovieReview> getUserMovieReviewByUserAndMovie(User user, Movie movie) {

		String query = "from UserMovieReview " + "where user_id = :userId " + "and movie_id = :movieId";

		List list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("userId", user.getId()).setParameter("movieId", movie.getId())
				.list();
		return list;
	}

	@Override
	public List<UserMovieReview> getAllMovieReviews(Movie movie) {

		String query = "from UserMovieReview " + "where movie_id = :movieId";

		List list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("movieId", movie.getId()).list();
		return list;
	}

	@Override
	public List<UserMovieReview> getUserMovieReviewByUser(User user) {

		String query = "from UserMovieReview umr " + "join umr.movie " + "where user_id = :userId " + "order by umr.created desc";

		List<Object[]> list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("userId", user.getId()).list();

		List<UserMovieReview> result = new ArrayList<UserMovieReview>();
		for (Object[] arr : list) {

			UserMovieReview umr = (UserMovieReview) arr[0];
			Movie m = (Movie) arr[1];

			umr.setMovie(m);

			result.add(umr);
		}

		return result;
	}

}
