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
	private IMovieService movieService;

	/**
	 * getImage
	 * @return
	 */
	public StreamedContent getImage() {

		final FacesContext fc = FacesContext.getCurrentInstance();
		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		final String actorId = fc.getExternalContext().getRequestParameterMap().get("actor_id");

		if ("".equals(actorId)) {

			return new DefaultStreamedContent();
		}

		final Actor a = getMovieService().getActorById(Integer.parseInt(actorId));
		final byte[] photoData = a.getImage();

		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
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
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

}
