package hr.primefaces.dao.impl;

import hr.primefaces.dao.IActorDAO;
import hr.primefaces.model.Actor;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ActorDAO implements IActorDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_ACTOR_BY_ID = "from Actor where id = :actor_id";
	private final String GET_ACTOR_BY_NAME = "from Actor where firstname like :name";
	private final String GET_ACTORS = "from Actor";

	@Override
	public void addActor(final Actor p_actor) {
		getSessionFactory().getCurrentSession().save(p_actor);
	}

	@Override
	public void deleteActor(final Actor p_actor) {
		getSessionFactory().getCurrentSession().delete(p_actor);
	}

	@Override
	public void updateActor(final Actor p_actor) {
		getSessionFactory().getCurrentSession().update(p_actor);
	}

	@Override
	public Actor getActorById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_ACTOR_BY_ID);
		query.setParameter("actor_id", p_id);
		return (Actor) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> getActorByName(final String p_name) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_ACTOR_BY_NAME);
		query.setParameter("name", "%" + p_name.toLowerCase() + "%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> getActors() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_ACTORS);
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
