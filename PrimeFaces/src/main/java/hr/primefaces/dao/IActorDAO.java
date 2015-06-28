package hr.primefaces.dao;

import hr.primefaces.model.Actor;

import java.util.List;

public interface IActorDAO {

	public void addActor(Actor actor);

	public void updateActor(Actor actor);

	public void deleteActor(Actor actor);

	public Actor getActorById(int id);
	
	public List<Actor> getActorByName(String username);

	public List<Actor> getActors();
}
