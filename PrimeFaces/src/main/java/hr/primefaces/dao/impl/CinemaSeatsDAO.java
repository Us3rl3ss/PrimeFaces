package hr.primefaces.dao.impl;

import hr.primefaces.dao.ICinemaSeatsDAO;
import hr.primefaces.model.CinemaSeats;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class CinemaSeatsDAO implements ICinemaSeatsDAO {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public CinemaSeatsDAO() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCinemaSeats(CinemaSeats cinemaSeats) {
		getSessionFactory().getCurrentSession().save(cinemaSeats);
	}

	@Override
	public void deleteCinemaSeats(CinemaSeats cinemaSeats) {
		getSessionFactory().getCurrentSession().delete(cinemaSeats);
	}

	@Override
	public void updateCinemaSeats(CinemaSeats cinemaSeats) {
		getSessionFactory().getCurrentSession().update(cinemaSeats);
	}

	@Override
	public CinemaSeats getCinemaSeatsById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from CinemaSeats where id=?").setParameter(0, id)
				.list();
		return (CinemaSeats) list.get(0);
	}

	@Override
	public List<CinemaSeats> getCinemaSeats() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from CinemaSeats").list();
		return list;
	}

	@Override
	public List<CinemaSeats> getCinemaSeatsByName(String name) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from CinemaSeats where name like lower('%"
								+ name.toLowerCase() + "%')").list();
		return list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public List<CinemaSeats> getCinemaSeatsByCinemaId(int cinemaId) {

		Query query = getSessionFactory().getCurrentSession().createQuery(
				"from CinemaSeats where cinema_id = :id");
		query.setLong("id", cinemaId);

		List list = query.list();
		
		return list;
	}

}
