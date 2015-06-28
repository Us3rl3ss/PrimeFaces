package hr.primefaces.service;

import hr.primefaces.model.Actor;

import java.util.List;

public interface IActorService {
	
	public void addActor(Actor actor);
	
	public void updateActor(Actor actor);

	public void deleteActor(Actor actor);
	
	public Actor getActorById(int id);
	
	public List<Actor> getActorByName(String name);
	
	public List<Actor> getActors();
}
