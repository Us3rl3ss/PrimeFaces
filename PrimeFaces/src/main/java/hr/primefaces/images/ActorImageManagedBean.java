package hr.primefaces.images;

import hr.primefaces.model.Actor;
import hr.primefaces.service.IMovieService;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "actorImageMB")
@ApplicationScoped
public class ActorImageManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	public StreamedContent getImage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		String actor_id = fc.getExternalContext().getRequestParameterMap().get("actor_id");

		if ("".equals(actor_id))
			return new DefaultStreamedContent();

		Actor a = movieService.getActorById(Integer.parseInt(actor_id));

		byte[] photoData = a.getImage();
		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
	}

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @param movieService
	 *            the movieService to set
	 */
	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

}
