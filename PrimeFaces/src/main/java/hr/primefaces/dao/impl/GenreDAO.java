package hr.primefaces.dao.impl;

import hr.primefaces.dao.IGenreDAO;
import hr.primefaces.model.Genre;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDAO implements IGenreDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_GENRE_BY_ID = "from Genre where id = :genre_id";
	private final String GET_GENRES = "from Genre";
	private final String GET_GENRE_BY_NAME = "from Genre where name like :name";

	public GenreDAO() {
	}

	@Override
	public void addGenre(final Genre p_genre) {
		getSessionFactory().getCurrentSession().save(p_genre);
	}

	@Override
	public void deleteGenre(final Genre p_genre) {
		getSessionFactory().getCurrentSession().delete(p_genre);
	}

	@Override
	public void updateGenre(final Genre p_genre) {
		getSessionFactory().getCurrentSession().update(p_genre);
	}

	@Override
	public Genre getGenreById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_GENRE_BY_ID);
		query.setParameter("genre_id", p_id);
		return (Genre) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> getGenres() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_GENRES);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> getGenreByName(final String p_name) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_GENRE_BY_NAME);
		query.setParameter("name", "%" + p_name.toLowerCase() + "%");
		return query.list();
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
