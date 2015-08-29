package hr.primefaces.converter;

import hr.primefaces.model.Movie;
import hr.primefaces.service.IMovieService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@ManagedBean
@RequestScoped
public class MovieConverter implements Converter {

	@ManagedProperty("#{MovieService}")
	private IMovieService movieService;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && value.trim().length() > 0) {
			try {
				return movieService.getMovieById(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
			}
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Movie) object).getId());
		} else {
			return null;
		}
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

}
