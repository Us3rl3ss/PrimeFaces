package hr.primefaces.converter;

import hr.primefaces.model.Genre;
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
public class GenreConverter implements Converter {

	@ManagedProperty("#{MovieService}")
	private IMovieService movieService;

	/**
	 * getAsObject
	 */
	public Object getAsObject(final FacesContext p_fc, final UIComponent p_uic, final String p_value) {

		if (p_value != null && p_value.trim().length() > 0) {

			try {
				return getMovieService().getGenreById(Integer.parseInt(p_value));
			}
			catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
			}
		}
		else {
			return null;
		}
	}

	/**
	 * getAsString
	 */
	public String getAsString(final FacesContext p_fc, final UIComponent p_uic, final Object p_object) {

		if (p_object != null) {
			return String.valueOf(((Genre) p_object).getId());
		}
		else {
			return null;
		}
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
	 * @param p_movieService
	 *            the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

}
