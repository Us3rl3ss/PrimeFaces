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
	IMovieService movieService;

	private List<Actor> actorList;

	@PostConstruct
	public void init() {
		actorList = movieService.getActors();
	}

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
	 * @param movieService
	 *            the movieService to set
	 */
	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * @param actorList
	 *            the actorList to set
	 */
	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

}