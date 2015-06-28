package hr.primefaces.images;

import hr.primefaces.model.Movie;
import hr.primefaces.service.IMovieService;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "movieImageMB")
@ApplicationScoped
public class MovieImageManagedBean {

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	public StreamedContent getImage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		String movie_id = fc.getExternalContext().getRequestParameterMap().get("movie_id");

		Movie m = movieService.getMovieById(Integer.parseInt(movie_id));

		byte[] photoData = m.getImage(); // userPhotoJpaController.findUserPhoto(Integer.parseInt(id)).getPhotoData();
		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

}
