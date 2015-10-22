package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.service.IMovieService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "getActorMB")
@ViewScoped
public class GetActorView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	private List<Actor> actorList;

	@PostConstruct
	public void init() {

		setActorList(getMovieService().getActors());
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the actorList
	 */
	public List<Actor> getActorList() {
		return actorList;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_actorList the actorList to set
	 */
	public void setActorList(final List<Actor> p_actorList) {
		this.actorList = p_actorList;
	}

}