package hr.primefaces.dao.impl;

import hr.primefaces.dao.ICinemaDAO;
import hr.primefaces.model.Cinema;

import java.util.List;

import org.hibernate.SessionFactory;

public class CinemaDAO implements ICinemaDAO {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public CinemaDAO() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCinema(Cinema cinema) {
		getSessionFactory().getCurrentSession().save(cinema);
	}

	@Override
	public void deleteCinema(Cinema cinema) {
		getSessionFactory().getCurrentSession().delete(cinema);
	}

	@Override
	public void updateCinema(Cinema cinema) {
		getSessionFactory().getCurrentSession().update(cinema);
	}

	@Override
	public Cinema getCinemaById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Cinema where id=?").setParameter(0, id)
				.list();
		return (Cinema) list.get(0);
	}

	@Override
	public List<Cinema> getCinemas() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Cinema").list();
		return list;
	}

	@Override
	public List<Cinema> getCinemaByName(String name) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from Cinema where name like lower('%"
								+ name.toLowerCase() + "%')").list();
		return list;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
