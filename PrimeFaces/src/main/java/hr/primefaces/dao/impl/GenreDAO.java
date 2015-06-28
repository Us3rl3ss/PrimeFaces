package hr.primefaces.dao.impl;

import hr.primefaces.dao.IGenreDAO;
import hr.primefaces.model.Genre;

import java.util.List;

import org.hibernate.SessionFactory;

public class GenreDAO implements IGenreDAO {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public GenreDAO() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addGenre(Genre genre) {
		getSessionFactory().getCurrentSession().save(genre);
	}

	@Override
	public void deleteGenre(Genre genre) {
		getSessionFactory().getCurrentSession().delete(genre);
	}

	@Override
	public void updateGenre(Genre genre) {
		getSessionFactory().getCurrentSession().update(genre);
	}

	@Override
	public Genre getGenreById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Genre where id=?").setParameter(0, id)
				.list();
		return (Genre) list.get(0);
	}

	@Override
	public List<Genre> getGenres() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Genre").list();
		return list;
	}

	@Override
	public List<Genre> getGenreByName(String name) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from Genre where name like lower('%"
								+ name.toLowerCase() + "%')").list();
		return list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
