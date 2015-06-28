package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.service.IActorService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "getActorMB")
@ViewScoped
public class GetActorManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ActorService}")
	IActorService actorService;

	private Actor actor = new Actor();

	private List<Actor> actorList;

	@PostConstruct
	public void init() {
		actorList = actorService.getActors();
	}

	public IActorService getActorService() {
		return actorService;
	}

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}