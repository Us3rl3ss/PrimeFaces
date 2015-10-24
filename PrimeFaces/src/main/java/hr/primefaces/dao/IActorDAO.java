package hr.primefaces.dao;

import hr.primefaces.model.Actor;

import java.util.List;

public interface IActorDAO {

	void addActor(Actor p_actor);

	void updateActor(Actor p_actor);

	void deleteActor(Actor p_actor);

	Actor getActorById(int p_id);

	List<Actor> getActorByName(String p_username);

	List<Actor> getActors();

}
