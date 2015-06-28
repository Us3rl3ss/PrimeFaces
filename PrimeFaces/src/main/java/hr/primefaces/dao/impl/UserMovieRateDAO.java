package hr.primefaces.dao.impl;

import java.util.ArrayList;
import java.util.List;

import hr.primefaces.dao.IUserMovieRateDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieRate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserMovieRateDAO implements IUserMovieRateDAO {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUserMovieRate(UserMovieRate userMovieRate) {
		getSessionFactory().getCurrentSession().save(userMovieRate);
	}

	@Override
	public void deleteUserMovieRate(UserMovieRate userMovieRate) {
		getSessionFactory().getCurrentSession().delete(userMovieRate);
	}

	@Override
	public void updateUserMovieRate(UserMovieRate userMovieRate) {
		getSessionFactory().getCurrentSession().update(userMovieRate);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public List<UserMovieRate> getUserMovieRateByUserAndMovie(User user, Movie movie) {
		
		String query = "from UserMovieRate "
				+ "where user_id = :userId "
				+ "and movie_id = :movieId";
		
		
		List list = getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter("userId", user.getId())
				.setParameter("movieId", movie.getId())
				.list();
		return list;
	}
	
	@Override
	public Double getAverageRateByMovie(Movie movie) {
		
		String query = "select ROUND(avg(rate)) "
				+ "from UserMovieRate "
				+ "where movie_id = :movieId";
		
		
		Double result = (Double) getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter("movieId", movie.getId()).uniqueResult();
		return result;
	}
	
	@Override
	public List<UserMovieRate> getUserMovieRateByUser(User user) {
		
		String query = "from UserMovieRate usr "
				+ "join usr.movie "
				+ "where user_id = :userId "
				+ "order by usr.created desc";
		
		
		List<Object[]> list = getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter("userId", user.getId())
				.list();
		
		List<UserMovieRate> result = new ArrayList<UserMovieRate>();
		for (Object[] arr : list) {

			UserMovieRate umr = (UserMovieRate) arr[0];
			Movie m = (Movie) arr[1];

			umr.setMovie(m);
			
			result.add(umr);
		}
		
		return result;
	}

}
