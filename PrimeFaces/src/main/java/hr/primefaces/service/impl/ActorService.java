package hr.primefaces.service.impl;

import hr.primefaces.dao.IActorDAO;
import hr.primefaces.model.Actor;
import hr.primefaces.service.IActorService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "ActorService")
@ApplicationScoped
@Transactional(readOnly = true)
public class ActorService implements IActorService, Serializable {

	private static final long serialVersionUID = 1L;

	IActorDAO actorDAO;

	@Transactional(readOnly = false)
	@Override
	public void addActor(Actor actor) {
		getActorDAO().addActor(actor);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteActor(Actor actor) {
		getActorDAO().deleteActor(actor);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateActor(Actor actor) {
		getActorDAO().updateActor(actor);
	}

	@Override
	public Actor getActorById(int id) {
		return getActorDAO().getActorById(id);
	}

	@Override
	public List<Actor> getActorByName(String name) {
		return getActorDAO().getActorByName(name);
	}

	@Override
	public List<Actor> getActors() {
		return getActorDAO().getActors();
	}

	public IActorDAO getActorDAO() {
		return actorDAO;
	}

	public void setActorDAO(IActorDAO actorDAO) {
		this.actorDAO = actorDAO;
	}

}
