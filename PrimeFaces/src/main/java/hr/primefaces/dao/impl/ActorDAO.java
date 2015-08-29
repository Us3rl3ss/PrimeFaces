package hr.primefaces.dao.impl;

import hr.primefaces.dao.IActorDAO;
import hr.primefaces.model.Actor;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ActorDAO implements IActorDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addActor(Actor actor) {
		getSessionFactory().getCurrentSession().save(actor);
	}

	@Override
	public void deleteActor(Actor actor) {
		getSessionFactory().getCurrentSession().delete(actor);
	}

	@Override
	public void updateActor(Actor actor) {
		getSessionFactory().getCurrentSession().update(actor);
	}

	@Override
	public Actor getActorById(int id) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Actor where id=?").setParameter(0, id).list();
		return (Actor) list.get(0);
	}

	@Override
	public List<Actor> getActorByName(String name) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Actor where firstname like lower('%" + name.toLowerCase() + "%')").list();
		return list;
	}

	@Override
	public List<Actor> getActors() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Actor").list();
		return list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
